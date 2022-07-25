package ru.otus.springintegration.service;

import lombok.val;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import ru.otus.springintegration.model.DadataResponseDto;

@Service
public class MailService {

    public Message<String> getMailPayloadToConsumer(Message<DadataResponseDto> messageIn) {
        val addressCompany = messageIn.getPayload();
        val message = new StringBuilder("Добрый день, уважаемый пользователь нашей системы").append("\r\n")
                .append("Мы шлем вам информацию о нашей компании:").append("\r\n")
                .append("Адресс: ").append(addressCompany.getPostalCode()).append(", ")
                .append(addressCompany.getResult()).append("\r\n")
                .append("Идентификатор ФИАС: ").append(addressCompany.getFiasId()).toString();

        Message<String> messageOut = MessageBuilder.withPayload(message).build();
        return messageOut;
    }
}
