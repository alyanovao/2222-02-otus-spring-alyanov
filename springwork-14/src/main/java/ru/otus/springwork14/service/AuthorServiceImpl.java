package ru.otus.springwork14.service;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.otus.springwork14.model.Author;
import ru.otus.springwork14.model.AuthorDto;

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {

    @Override
    public AuthorDto toDto(Author author) {
        val authorDto = new AuthorDto(author.getFirstName(), author.getLastName(), author.getPatronymic());
        log.info("Author :: " + author.getFirstName());
        return authorDto;
    }
}
