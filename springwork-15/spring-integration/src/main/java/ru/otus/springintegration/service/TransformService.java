package ru.otus.springintegration.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.val;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import ru.otus.springintegration.model.DadataResponseDto;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TransformService {
    public Message<String> toDadataAddressRequest(Message messageIn) {
        Gson gson = new GsonBuilder().create();
        val address = messageIn.getPayload().toString();
        val response = gson.toJson(Collections.singletonList(address), new TypeToken<ArrayList<String>>() {}.getType());
        Message<String> messageOut = MessageBuilder.withPayload(response)
                .build();
        return messageOut;
    }

    public Message<DadataResponseDto> toDadataAddressResponse(String messageIn) {
        Gson gson = new GsonBuilder().create();
        Type listOfDadataResponseDto = new TypeToken<ArrayList<DadataResponseDto>>() {}.getType();
        List<DadataResponseDto> list = gson.fromJson(messageIn, listOfDadataResponseDto);
        return new GenericMessage<DadataResponseDto>(list.get(0));
    }

    public String toMailSender(Message<DadataResponseDto> messageIn) {
        return "Test";
    }
}
