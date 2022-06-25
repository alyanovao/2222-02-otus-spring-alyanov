package ru.otus.springwork12.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

@NoArgsConstructor
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
