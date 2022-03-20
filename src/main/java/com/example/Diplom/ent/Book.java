package com.example.Diplom.ent;

import com.example.Diplom.dto.request.addbook.ApiBook;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(schema = "public", name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(nullable = false)
    private String bookName;

    @Column(nullable = false)
    private int pages;

    @Column(nullable = false)
    private Float cost;

    @Column(nullable = false)
    private int dateOfPrinting;

    @OneToOne
    @JoinColumn(name = "author_id")
    private BookAuthor bookAuthor;


    public Book(ApiBook book, BookAuthor bookAuthor) {
        this.bookName = book.getBookName();
        this.pages = book.getPages();
        this.cost = book.getCost();
        this.dateOfPrinting = book.getDateOfPrinting();
        this.bookAuthor = bookAuthor;
    }
}


