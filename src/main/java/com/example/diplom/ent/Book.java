package com.example.diplom.ent;

import com.example.diplom.dto.request.addbook.ApiBook;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "author_id")
    private BookAuthor bookAuthor;

    public Book(String bookName, int pages, Float cost, int dateOfPrinting, BookAuthor bookAuthor) {
        this.bookName = bookName;
        this.pages = pages;
        this.cost = cost;
        this.dateOfPrinting = dateOfPrinting;
        this.bookAuthor = bookAuthor;
    }

    public Book(ApiBook book, BookAuthor bookAuthor) {
        this.bookName = book.getBookName();
        this.pages = book.getPages();
        this.cost = book.getCost();
        this.dateOfPrinting = book.getDateOfPrinting();
        this.bookAuthor = bookAuthor;
    }
}


