package ru.otus.springintegration.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

import java.io.File;

@MessagingGateway(errorChannel = "integration.send.error.channel")
public interface SendServiceGateWay {

    @Gateway(requestChannel = "integration.inbound.fileChannel", replyChannel = "integration.inbound.messageChannel")
    String process(File s);

    @Gateway(requestChannel = "integration.send.error.timeoutError.channel")
    Message handleTimeoutErrorFlow(Message<?> message);

    @Gateway(requestChannel = "integration.send.error.clientError.channel")
    Message handleClientErrorFlow(Message<?> message);

    @Gateway(requestChannel = "integration.send.error.sendMailError.channel")
    Message handleSendMailErrorFlow(Message<?> message);

    @Gateway(requestChannel = "integration.send.error.applicationError.channel")
    Message handleApplicationErrorFlow(Message<?> message);
}