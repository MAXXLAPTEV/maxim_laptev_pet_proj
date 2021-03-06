package com.example.diplom.dto.response;

import com.example.diplom.ent.BookAuthor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class BookResponse {
    private Long id;
    private String bookName;
    private int pages;
    private Float cost;
    private int dateOfPrinting;
    private BookAuthor bookAuthor;

}
