package ru.otus.springwork09.model;

import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookSimpleDto {
    private long id;
    private String name;
    private Author author;
    private KindBook kind;
    private Commentary commentary;

    public static BookSimpleDto toDto(Book book) {
        return new BookSimpleDto(book.getId(), book.getName(),
                book.getAuthors().stream().findFirst().orElse(new Author()),
                book.getKind().stream().findFirst().orElse(new KindBook()),
                book.getCommentary().stream().findFirst().orElse(new Commentary()));
    }

    public static Book fromDto(BookSimpleDto bookSimpleDto) {
        val commentary = bookSimpleDto.getCommentary();
        if (commentary != null) {
            commentary.setBookId(bookSimpleDto.getId());
        }
        return new Book(bookSimpleDto.getId(), bookSimpleDto.getName(),
                new ArrayList<>(Arrays.asList(bookSimpleDto.getAuthor())),
                new ArrayList<>(Arrays.asList(bookSimpleDto.getKind())),
                new ArrayList<>(Arrays.asList(commentary)));
    }
}
