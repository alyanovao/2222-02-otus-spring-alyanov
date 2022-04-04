package ru.otus.springwork04.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.springwork04.domain.Person;

@Component
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {

    private final StudentService service;

    @Override
    public Person login() {
        return service.getPerson();
    }
}
