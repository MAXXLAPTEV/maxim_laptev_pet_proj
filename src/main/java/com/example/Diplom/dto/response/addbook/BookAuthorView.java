package com.example.Diplom.dto.response.addbook;

import lombok.Data;

@Data
public class BookAuthorView {
    private Long id;
    private boolean isNew;

    public BookAuthorView(Long id, boolean isNew) {
        this.id = id;
        this.isNew = isNew;
    }
}
