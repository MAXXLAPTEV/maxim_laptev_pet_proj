package com.example.diplom.dto.response.addbook;

import lombok.Data;

@Data
public class BookView {
    private Long id;
    private String name;

    public BookView(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
