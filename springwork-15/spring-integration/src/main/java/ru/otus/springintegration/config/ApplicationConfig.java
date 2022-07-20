package ru.otus.springintegration.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.file.transformer.FileToStringTransformer;
import org.springframework.integration.http.HttpHeaders;
import org.springframework.integration.http.dsl.Http;
import org.springframework.integration.mail.dsl.Mail;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import ru.otus.springintegration.gateway.SendServiceGateWay;

@Configuration
@EnableIntegration
@Slf4j
public class ApplicationConfig {

    @Autowired
    private SendServiceGateWay sendServiceGateWay;

    @Value("${send.httpClient.timeout}")
    private int timeOutInMilliSec;

    @Value("${send.httpClient.dadataCleanAddressUri}")
    private String dadataCleanAddressUri;

    @Value("${send.httpClient.token}")
    private String token;

    @Value("${send.httpClient.secret}")
    private String secret;

    @Value("${send.mail.host}")
    private String smtpHost;

    @Value("${send.mail.port}")
    private int smtpPort;

    @Value("${send.mail.username}")
    private String username;

    @Value("${send.mail.password}")
    private String password;

    @Value("${send.mail.smtp.auth}")
    private String smtpAuth;

    @Value("${send.mail.smtp.starttls.enable}")
    private String smtpTls;

    @Value("${send.mail.smtp.debug}")
    private String smtpDebug;

    @Value("${send.mail.letter.subject}")
    private String subject;

    @Bean("integration.inbound.fileChannel")
    public MessageChannel fileChannel() {
        return new QueueChannel();
    }

    @Bean("integration.inbound.messageChannel")
    public MessageChannel messageChannel() {
        return new QueueChannel();
    }

    @Bean
    public FileToStringTransformer fileToStringTransformer() {
        return new FileToStringTransformer();
    }

    @Bean
    public IntegrationFlow handleRecovery() {
        return IntegrationFlows.from("integration.send.error.channel")
                .channel("error.transformer.input")
                .get();
    }

    @Transformer(inputChannel = "error.transformer.input")
    public Message transformer(Message<?> message) {
        Message<?> msg;

        log.info("message: " + message.getPayload());

        if(message.getPayload().toString().contains("ResourceAccessException"))
            msg = sendServiceGateWay.handleTimeoutErrorFlow(message);
        else if(message.getPayload().toString().contains("MailSendException"))
            msg = sendServiceGateWay.handleSendMailErrorFlow(message);
        else
            msg = sendServiceGateWay.handleClientErrorFlow(message);
        return msg;
    }

    @Bean
    public HttpComponentsClientHttpRequestFactory getClientHttpRequestFactoryForPayments() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(timeOutInMilliSec);
        clientHttpRequestFactory.setReadTimeout(timeOutInMilliSec);
        return clientHttpRequestFactory;
    }

    @Bean
    public IntegrationFlow handleTimeoutErrorFlow() {
        return IntegrationFlows.from("integration.send.error.timeoutError.channel")
                .transform(Throwable::getCause)
                .<ResourceAccessException>handle((p, h) ->
                        MessageBuilder.withPayload(p.getLocalizedMessage())
                                .setHeader(HttpHeaders.STATUS_CODE, HttpStatus.GATEWAY_TIMEOUT)
                                .build())
                .log()
                .get();
    }

    @Bean
    public IntegrationFlow handleClientErrorFlow() {
        return IntegrationFlows.from("integration.send.error.clientError.channel")
                .transform(Throwable::getCause)
                .<HttpStatusCodeException>handle((p, h) ->
                        MessageBuilder.withPayload(p.getResponseBodyAsString())
                                .setHeader(HttpHeaders.STATUS_CODE, p.getStatusCode())
                                .build())
                .log()
                .get();
    }

    @Bean
    public IntegrationFlow handleSendMailErrorFlow() {
        return IntegrationFlows.from("integration.send.error.sendMailError.channel")
                .transform(Throwable::getCause)
                .<Exception>handle((p, h) ->
                        MessageBuilder.withPayload(p.getLocalizedMessage())
                                .setHeader(HttpHeaders.STATUS_CODE, HttpStatus.NOT_FOUND)
                                .build())
                .log()
                .get();
    }

    @Bean
    public IntegrationFlow fileReadFlow() {
        return IntegrationFlows.from("integration.inbound.fileChannel")
                .transform(fileToStringTransformer())
                .split(s -> s.applySequence(false).delimiters("\r\n"))
                .handle("transformService", "toDadataAddressRequest")
                .log()
                .enrichHeaders(h -> h
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Token " + token)
                        .header("X-Secret", secret)
                )
                .handle(
                        Http.outboundGateway(dadataCleanAddressUri)
                                .httpMethod(HttpMethod.POST)
                                .mappedRequestHeaders("*")
                                .extractPayload(true)
                                .requestFactory(getClientHttpRequestFactoryForPayments())
                                .expectedResponseType(String.class)
                )
                .log()
                .handle("transformService", "toDadataAddressResponse")
                .log()
                .handle("mailService", "getMailPayloadToConsumer")
                .log()
                .enrichHeaders(Mail.headers()
                        .subject(subject)
                        .from(username)
                        .toFunction(m -> new String[] {username}))
                .handle(Mail.outboundAdapter(smtpHost)
                        .port(smtpPort)
                        .protocol("smtp")
                        .credentials(username, password)
                        .javaMailProperties(p -> p.put("mail.debug", smtpDebug)
                                .put("mail.smtps.auth", smtpAuth)
                                .put("mail.smtp.starttls.enable", smtpTls)
                                .put("mail.smtp.ssl.trust", smtpHost)
                        )
                )
                .get();
    }
}
