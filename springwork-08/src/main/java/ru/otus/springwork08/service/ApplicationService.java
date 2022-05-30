package ru.otus.springwork08.service;

import org.springframework.shell.standard.ShellMethod;

public interface ApplicationService {
    void getAuthors();

    void saveAuthor();

    void mergeAuthor();

    void getKindBooks();

    void saveKindBook();

    void updateKindBook();

    void getBookById();

    void getBooks();

    void saveBook();

    void mergeBook();

    void deleteBook();

    void getCommentaryById();

    void getAllCommentaries();

    void saveCommentary();

    void updateCommentary();

    void deleteCommentary();
}
