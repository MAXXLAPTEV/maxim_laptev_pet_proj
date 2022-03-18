package com.example.Diplom.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookAuthorRequest {
    private String authorName;
    private String authorSurname;
    private int authorBirth;
}
