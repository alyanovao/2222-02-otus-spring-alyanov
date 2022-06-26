package ru.otus.springwork11.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Document("commentary")
public class Commentary {
    public Commentary(String value, String bookId) {
        this.value = value;
        this.bookId = bookId;
    }

    @Id
    private String id;
    private String value;
    private String bookId;
}
