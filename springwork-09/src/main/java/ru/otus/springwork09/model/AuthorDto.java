package ru.otus.springwork09.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
    private long id;
    private String name;

    public static AuthorDto toDto(Author author) {
        return new AuthorDto(author.getId(), author.getFirstName() + " " + author.getLastName() + " " + author.getPatronymic());
    }
}
