package ru.otus.springcamel.route;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.ListJacksonDataFormat;
import org.springframework.stereotype.Component;
import ru.otus.springcamel.model.ResponseDto;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class FileTransformRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        errorHandler(defaultErrorHandler());

        onException(Exception.class)
                .handled(true)
                .log(LoggingLevel.ERROR, "onException: ${body}")
        .end();

        from("file://{{source.path}}?fileName=address.csv&noop=true&delay=1000")
                .routeId("fileInboundRouteId")
                .log("transform: ${body}")
                .split(body().tokenize("\r\n"))
                .log("split :: ${body}")
                .to("direct:dadataServerRoute")
                .to("direct:sendMailToConsumer")
        .end();

        from("direct:dadataServerRoute")
                .routeId("DadataRouteId")
                .process(exchange -> {
                    val message = exchange.getIn();
                    val body = message.getBody();
                    log.info("process :: " + body);
                    message.setBody("[\"" + body + "\"]");
                    message.setHeader("Authorization", "Token efabf40d6aa4c366ab2412a1dbb85438902023f8");
                    message.setHeader(Exchange.CONTENT_TYPE, "application/json;charset=\"" + StandardCharsets.UTF_8 + "\"");
                    message.setHeader("X-Secret", "7417107077b22c3cfbcc6fa5f07d8bf56c4f8a85");
                    message.setHeader(Exchange.HTTP_METHOD, "POST");
                    exchange.setIn(message);
                })
                .log("request :: ${body}")
                .toD("https://{{send.dadataHost}}:{{send.dadataPort}}{{send.dadataAddresUrl}}?throwExceptionOnFailure=false")
                .convertBodyTo(String.class)
                .log("response :: ${body}")
                .unmarshal(new ListJacksonDataFormat(ResponseDto.class))
        .end();

        from("direct:sendMailToConsumer")
                .routeId("sendMailToConsumerId")
                .log("sendMail :: ${body}")
                .removeHeaders("*")
                .process(exchange -> {
                    val body = exchange.getIn().getBody(ResponseDto.class);
                    val message = new StringBuilder("Добрый день, уважаемый пользователь нашей системы").append("\r\n")
                            .append("Мы шлем вам информацию о нашей компании:").append("\r\n")
                            .append("Адрес: " + body.getPostalCode() + ", " + body.getResult()).append("\r\n")
                            .append("Идентификатор ФИАС: " + body.getFias()).toString();
                    exchange.getIn().setBody(message);
                })
                .setHeader("Subject", constant("Информация о нашей организации"))
                .setHeader("To", constant("{{mail.username}}"))
                .setHeader("From", constant("{{mail.username}}"))
                .to("smtp://{{mail.host}}:{{mail.port}}?" +
                        "password={{mail.password}}" +
                        "&username={{mail.username}}" +
                        "&mail.smtps.auth=true" +
                        "&mail.smtp.starttls.enable=true" +
                        "&mail.smtp.ssl.trust={{mail.host}}")
        .end();


    }
}
