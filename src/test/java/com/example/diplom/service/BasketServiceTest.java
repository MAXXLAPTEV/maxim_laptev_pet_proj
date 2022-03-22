package com.example.diplom.service;

import com.example.diplom.dto.request.AddBookToBasketRequest;
import com.example.diplom.dto.request.BasketRequest;
import com.example.diplom.dto.response.BasketResponse;
import com.example.diplom.ent.Basket;
import com.example.diplom.ent.Book;
import com.example.diplom.ent.BookAuthor;
import com.example.diplom.ent.Customer;
import com.example.diplom.repo.BasketRepo;
import com.example.diplom.repo.BookAuthorRepo;
import com.example.diplom.repo.BookRepo;
import com.example.diplom.repo.CustomerRepo;
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
    void shouldCreateBasket() {
        Customer customer = customerRepo.save(new Customer("max", "laptev", "laptev@mail.com"));
        BasketRequest basket = new BasketRequest(customer.getId());

        assertEquals(basket.getCustomerId(), customer.getId());
    }

    @Test
    void shouldAddBookToTheBasket() {
        Customer customer = customerRepo.save(new Customer("max", "laptev", "laptev@mail.com"));

        BookAuthor bookAuthor = bookAuthorRepo.save(new BookAuthor("radagon", "marika", 1000));
        Book book = bookRepo.save(new Book("loyd", 123, 12.2F, 1222,
                bookAuthor));

        Basket basket = basketRepo.save(new Basket(customer, Collections.emptyList()));

        BasketResponse basketResponse = basketService.addBook(new AddBookToBasketRequest(basket.getId(), book.getId()));

        Basket actualBasket = basketRepo.findById(basketResponse.getId()).get();

        assertEquals("loyd", actualBasket.getBookList().get(0).getBookName());
    }
}
