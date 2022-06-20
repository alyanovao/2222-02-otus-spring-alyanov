package ru.otus.springwork11.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@NoArgsConstructor
@Document("kindbook")
public class KindBook {

    public KindBook(String name) {
        this.name = name;
    }

    @Id
    private String id;

    private String name;
}
