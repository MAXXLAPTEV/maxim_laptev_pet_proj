package com.example.diplom.dto.request.addbook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookRequest {
    private ApiBook book;
    private ApiBookAuthor bookAuthor;
}
