package com.example.Diplom.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class BookAuthorResponse {
    private Long id;
    private String authorName;
    private String authorSurname;
    private int authorBirth;
}
