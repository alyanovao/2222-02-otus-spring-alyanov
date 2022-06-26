package ru.otus.springwork11.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@NoArgsConstructor
@Getter
@Setter
@Document("author")
public class Author {

    public Author(String lastName, String firstName, String patronymic){
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
    }

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String patronymic;
}
