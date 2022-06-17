package ru.otus.springwork10.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.springwork10.model.Author;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    public static final Author author_1 = new Author(1, "authorFirstName", "authorLastName", "authorPatronymic");

    @Mock
    private AuthorService authorService;

    @Test
    void findById() {
        authorService.findById(1);
        verify(authorService, times(1)).findById(1);
    }

    @Test
    void findAll() {
        authorService.findAll();
        verify(authorService, times(1)).findAll();
    }

    @Test
    void save() {
        authorService.save(author_1);
        verify(authorService, times(1)).save(author_1);
    }
}