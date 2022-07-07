package ru.otus.springwork14.service;

import ru.otus.springwork14.model.Author;
import ru.otus.springwork14.model.AuthorDto;

public interface AuthorService {

    AuthorDto toDto(Author author);

}
