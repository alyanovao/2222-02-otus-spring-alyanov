package ru.otus.springintegration.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ru.otus.springintegration.gateway.SendServiceGateWay;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ControlService {

    @Value("${resource.filepath}")
    private String path;

    private final SendServiceGateWay gateWay;

    public void run() {

        try {
            Resource resource = new ClassPathResource(path);
            File file = new File(path);
            FileUtils.copyInputStreamToFile(resource.getInputStream(), file);
            gateWay.process(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
