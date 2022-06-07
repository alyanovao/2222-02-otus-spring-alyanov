package ru.otus.springwork09.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class BookDto {
    private long id;
    private String name;
    private String author;
    private String kind;
    private String commentary;

    public static BookDto toDto(Book book) {
        return new BookDto(book.getId(),
                book.getName(),
                book.getAuthors().stream().map(author -> {
                    return author.getFirstName() + " " +
                            author.getLastName() + " " +
                            author.getPatronymic();
                }).collect(Collectors.joining(", ")),
                book.getKind().stream().map(kind -> {
                    return kind.getName();
                }).collect(Collectors.joining(", ")),
                book.getCommentary().stream().map(commentary -> {
                    return commentary.getValue();
                }).collect(Collectors.joining(", "))
        );
    }
}
