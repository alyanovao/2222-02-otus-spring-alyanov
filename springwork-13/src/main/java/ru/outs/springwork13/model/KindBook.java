package ru.outs.springwork13.model;

import lombok.Getter;

import javax.persistence.*;


@Getter
@Entity
@Table(name = "kind")
public class KindBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "kind_name", nullable = false, unique = true)
    private String name;

    public KindBook() {
    }

    public KindBook(String name) {
        this.name = name;
    }

    public KindBook(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
