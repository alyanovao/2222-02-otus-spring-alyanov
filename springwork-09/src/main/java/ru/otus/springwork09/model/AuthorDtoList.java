package ru.otus.springwork09.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class AuthorDtoList {
    private List<AuthorDto> authorList;

    public AuthorDtoList() {
    }

    public AuthorDtoList(List<AuthorDto> authorList) {
        this.authorList = authorList;
    }
}
