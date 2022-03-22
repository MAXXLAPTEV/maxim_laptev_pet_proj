package com.example.diplom.ent;


import com.example.diplom.dto.request.addbook.ApiBookAuthor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "public", name = "author")

public class BookAuthor {
    public BookAuthor(String authorName, String authorSurname, int authorBirth) {
        this.authorName = authorName;
        this.authorSurname = authorSurname;
        this.authorBirth = authorBirth;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String authorName;

    @Column(nullable = false)
    private String authorSurname;

    @Column(nullable = false)
    private int authorBirth;

    public BookAuthor(ApiBookAuthor author) {
        this.authorName = author.getAuthorName();
        this.authorSurname = author.getAuthorSurname();
        this.authorBirth = author.getAuthorBirth();
    }
}
