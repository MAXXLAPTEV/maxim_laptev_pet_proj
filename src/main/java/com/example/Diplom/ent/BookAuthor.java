package com.example.Diplom.ent;


import com.example.Diplom.dto.request.addbook.ApiBookAuthor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(schema = "public", name = "author")

public class BookAuthor {

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
