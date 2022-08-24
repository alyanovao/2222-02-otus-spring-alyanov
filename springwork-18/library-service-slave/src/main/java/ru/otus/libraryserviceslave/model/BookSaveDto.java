package ru.otus.libraryserviceslave.model;

import lombok.Getter;

import java.util.List;

@Getter
public class BookSaveDto {
    private long id;
    private String name;
    private List<Integer> authorId;
    private List<Integer> kindId;
    private String commentary;

    public BookSaveDto(long id) {
        this.id = id;
    }

    public BookSaveDto(long id, String name, List<Integer> authorId, List<Integer> kindId, String commentary) {
        this.id = id;
        this.name = name;
        this.authorId = authorId;
        this.kindId = kindId;
        this.commentary = commentary;
    }
}
