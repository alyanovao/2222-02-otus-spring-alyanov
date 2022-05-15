package ru.otus.springwork07.service;

public interface ApplicationService {
    void getAuthors();

    void saveAuthor();

    void updateAuthor();

    void getKindBooks();

    void saveKindBook();

    void getBookById();

    void getBooksByParams();

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
