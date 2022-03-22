package com.example.diplom.dto.request.addbook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ApiBookAuthor{
private String authorName;

private String authorSurname;

private int authorBirth;

public String getAuthorName() {
        return authorName;
        }

public String getAuthorSurname() {
        return authorSurname;
        }

public int getAuthorBirth() {
        return authorBirth;
        }
        }
