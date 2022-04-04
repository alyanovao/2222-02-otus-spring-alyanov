package ru.otus.springwork04.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import ru.otus.springwork04.exceptions.NotFilledStudentException;

import java.io.IOException;
import java.io.InputStreamReader;

@Component
@RequiredArgsConstructor
public class InputStreamProviderImpl implements InputStreamProvider {

    private final PathSourcesProvider appConfig;

    @Override
    public InputStreamReader readRawData() {
        Resource resource = new ClassPathResource(appConfig.getPath());
        try {
            return new InputStreamReader(resource.getInputStream());
        } catch (IOException e) {
            throw new NotFilledStudentException(e);
        }
    }
}
