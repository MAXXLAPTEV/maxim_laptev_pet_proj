package com.example.diplom.service;


import com.example.diplom.dto.request.BookAuthorRequest;
import com.example.diplom.dto.request.addbook.ApiBook;
import com.example.diplom.dto.request.addbook.ApiBookAuthor;
import com.example.diplom.dto.request.addbook.BookRequest;
import com.example.diplom.dto.response.BookAuthorResponse;
import com.example.diplom.dto.response.BookResponse;
import com.example.diplom.dto.response.addbook.AddBookResponse;
import com.example.diplom.ent.Book;
import com.example.diplom.ent.BookAuthor;
import com.example.diplom.repo.BookAuthorRepo;
import com.example.diplom.repo.BookRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookAuthorService bookAuthorService;

    @Autowired
    private BookAuthorRepo bookAuthorRepo;

    @AfterEach
    void resetDB() {
        bookRepo.deleteAll();
        bookAuthorRepo.deleteAll();
    }

    @Test
    void shouldCreateBookWithAuthor() {
        BookRequest request = new BookRequest(new ApiBook("java for all", 120, 1200f, 1200),
                new ApiBookAuthor("maxim", "kortnev", 1988));
        AddBookResponse response = bookService.addBook(request);
        Book book = bookRepo.findById(response.getBookView().getId()).get();

        assertEquals("java for all", book.getBookName());
        assertEquals(120, book.getPages());
        assertEquals("maxim", book.getBookAuthor().getAuthorName());
        assertEquals(1988, book.getBookAuthor().getAuthorBirth());
    }

    @Test
    void shouldFindAuthorByNameAndSurname(){
        BookAuthorRequest request = new BookAuthorRequest("maxim","kkorol",1889);
        BookAuthorResponse response = bookAuthorService.addBookAuthor(request);
        BookAuthor author = bookAuthorRepo.findByAuthorNameEqualsAndAuthorSurnameEquals(response.getAuthorName(),
                response.getAuthorSurname()).get();

        assertEquals("maxim", author.getAuthorName());
        assertEquals("kkorol", author.getAuthorSurname());
    }

    @Test
    void shouldFindBookById(){
        BookAuthor bookAuthor = bookAuthorRepo.save(new BookAuthor( "radagon", "marika", 1000));
        Book book = bookRepo.save(new Book("loyd", 123, 12.2F ,1222,
                bookAuthor));

        BookResponse bookId = bookService.findByBookId(book.getId());

        assertEquals(book.getId(), bookId.getId());
        assertEquals("loyd", book.getBookName());
        assertEquals(123, book.getPages());
        assertEquals(12.2F, book.getCost());
        assertEquals(1222, book.getDateOfPrinting());
        assertEquals("radagon", book.getBookAuthor().getAuthorName());
        assertEquals("marika", book.getBookAuthor().getAuthorSurname());
        assertEquals(1000, book.getBookAuthor().getAuthorBirth());
    }

    @Test
    void shouldDeleteAuthorById(){
        BookAuthor author = bookAuthorRepo.save(new BookAuthor( "radagon", "marika", 1000));
        bookAuthorService.deleteAuthor(author.getId());
        Optional<BookAuthor> bookAuthor = bookAuthorRepo.findById(author.getId());

        assertEquals(false, bookAuthor.isPresent());
    }

    @Test
    void shouldDeleteBookById(){
        BookAuthor bookAuthor = bookAuthorRepo.save(new BookAuthor( "radagon", "marika", 1000));
        Book book = bookRepo.save(new Book("loyd", 123, 12.2F ,1222, bookAuthor));
        bookRepo.deleteById(book.getId());
        Optional<Book> book1 = bookRepo.findById(book.getId());

        assertEquals(false, book1.isPresent());

    }


}


