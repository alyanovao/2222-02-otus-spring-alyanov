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
                book.getAuthors().stream().map(author1 ->
                            author1.getFirstName() + " " +
                            author1.getLastName() + " " +
                            author1.getPatronymic()
                ).collect(Collectors.joining(", ")),
                book.getKind().stream().map(KindBook::getName).collect(Collectors.joining(", ")),
                book.getCommentary().stream().map(Commentary::getValue).collect(Collectors.joining(", "))
        );
    }
}
