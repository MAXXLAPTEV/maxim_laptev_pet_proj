package com.example.Diplom.dto.response.addbook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddBookResponse {
    private BookView bookView;
    private BookAuthorView authorView;
}
