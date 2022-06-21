package ru.otus.springwork11.model;

import lombok.Getter;

import java.util.List;

@Getter
public class BookSaveDto {
    private String id;
    private String name;
    private List<String> authorId;
    private List<String> kindId;
    private String commentary;

    public BookSaveDto(String id) {
        this.id = id;
    }

    public BookSaveDto(String id, String name, List<String> authorId, List<String> kindId, String commentary) {
        this.id = id;
        this.name = name;
        this.authorId = authorId;
        this.kindId = kindId;
        this.commentary = commentary;
    }
}
