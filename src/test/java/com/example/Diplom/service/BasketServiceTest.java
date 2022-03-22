package com.example.Diplom.service;

import com.example.Diplom.dto.request.AddBookToBasketRequest;
import com.example.Diplom.dto.request.BasketRequest;
import com.example.Diplom.dto.request.CustomerRequest;
import com.example.Diplom.dto.request.addbook.ApiBook;
import com.example.Diplom.dto.request.addbook.ApiBookAuthor;
import com.example.Diplom.dto.request.addbook.BookRequest;
import com.example.Diplom.dto.response.BasketResponse;
import com.example.Diplom.dto.response.addbook.AddBookResponse;
import com.example.Diplom.ent.Basket;
import com.example.Diplom.ent.Book;
import com.example.Diplom.ent.BookAuthor;
import com.example.Diplom.ent.Customer;
import com.example.Diplom.repo.BasketRepo;
import com.example.Diplom.repo.BookAuthorRepo;
import com.example.Diplom.repo.BookRepo;
import com.example.Diplom.repo.CustomerRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BasketServiceTest {

    @Autowired
    private BasketRepo basketRepo;

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private BasketService basketService;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    BookService bookService;

    @Autowired
    BookAuthorRepo bookAuthorRepo;

    @Test
    void shouldCreateBasket(){
        Customer customer = customerRepo.save(new Customer("max", "laptev", "laptev@mail.com"));
        BasketRequest basket = new BasketRequest(customer.getId());

        assertEquals(basket.getCustomerId(), customer.getId());
    }

    @Test
    void shouldAddBookToTheBasket(){
        Customer customer = customerRepo.save(new Customer("max", "laptev", "laptev@mail.com"));

        BookAuthor bookAuthor = bookAuthorRepo.save(new BookAuthor( "radagon", "marika", 1000));
        Book book = bookRepo.save(new Book("loyd", 123, 12.2F ,1222,
                bookAuthor));

        Basket basket = basketRepo.save(new Basket(customer, Collections.emptyList()));

        BasketResponse basketResponse = basketService.addBook(new AddBookToBasketRequest(basket.getId(), book.getId()));

        Basket actualBasket = basketRepo.findById(basketResponse.getId()).get();

        assertEquals("loyd",actualBasket.getBookList().get(0).getBookName());
    }
}
