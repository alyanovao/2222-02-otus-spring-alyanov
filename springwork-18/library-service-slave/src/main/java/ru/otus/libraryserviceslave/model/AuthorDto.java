package ru.otus.libraryserviceslave.model;

import lombok.Getter;

@Getter
public class AuthorDto {
    private long id;
    private String name;

    public AuthorDto(long id) {
        this.id = id;
    }

    public AuthorDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public AuthorDto() {
    }

    public static AuthorDto toDto(Author author) {
        return new AuthorDto(author.getId(), author.getFirstName() + " " + author.getLastName() + " " + author.getPatronymic());
    }
}
