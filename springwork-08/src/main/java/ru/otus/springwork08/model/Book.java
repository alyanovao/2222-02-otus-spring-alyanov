package ru.otus.springwork08.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document("book")
public class Book {

    public Book(String name, List<Author> authors, List<KindBook> kind, List<Commentary> commentary) {
        this.name = name;
        this.authors = authors;
        this.kind = kind;
        this.commentary = commentary;
    }

    @Id
    private String id;

    private String name;

    @DBRef
    private List<Author> authors;

    @DBRef
    private List<KindBook> kind;

    @DBRef
    private List<Commentary> commentary;
}
