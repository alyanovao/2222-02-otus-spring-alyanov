package ru.otus.libraryservice.model;

import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookModelDto {
    private long id;
    private String name;
    private List<Author> author;
    private List<KindBook> kind;
    private Commentary commentary;

    public static BookModelDto toDto(Book book) {
        return new BookModelDto(book.getId(), book.getName(),
                book.getAuthors().stream().collect(Collectors.toList()),
                book.getKind().stream().collect(Collectors.toList()),
                book.getCommentary().stream().findFirst().orElse(new Commentary()));
    }

    public static Book fromDto(BookModelDto bookSimpleDto) {
        val commentary = bookSimpleDto.getCommentary();
        if (commentary != null) {
            commentary.setBookId(bookSimpleDto.getId());
        }
        return new Book(bookSimpleDto.getId(), bookSimpleDto.getName(),
                bookSimpleDto.getAuthor(),
                bookSimpleDto.getKind(),
                new ArrayList<>(Arrays.asList(commentary)));
    }
}
