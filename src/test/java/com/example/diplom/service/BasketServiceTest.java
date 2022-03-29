package com.example.diplom.service;

import com.example.diplom.dto.request.AddBookToBasketRequest;
import com.example.diplom.dto.request.BasketRequest;
import com.example.diplom.dto.response.BasketResponse;
import com.example.diplom.dto.response.addbasket.BasketView;
import com.example.diplom.ent.Basket;
import com.example.diplom.ent.Book;
import com.example.diplom.ent.BookAuthor;
import com.example.diplom.ent.Customer;
import com.example.diplom.repo.BasketRepo;
import com.example.diplom.repo.BookAuthorRepo;
import com.example.diplom.repo.BookRepo;
import com.example.diplom.repo.CustomerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    @Transactional
    void shouldFindBasketById() {
        Customer customer = new Customer("Kill", "Bill", "@asd");
        List<Book> books = new ArrayList<>();
        books.add(new Book("book1", 123, 1f, 1000, null));
        Basket basket = new Basket(customer, books);
        basketRepo.save(basket);
        Long id = basket.getId();

        BasketView savedBasket = basketService.findBasket(id);

        assertEquals(id, savedBasket.getId());
        assertEquals(books.size(), savedBasket.getBooks().size());
        assertEquals(books.get(0).getBookName(), savedBasket.getBooks().get(0).getName());
    }

    @Test
    @Transactional
    void shouldCreateBasketFromService() {
        Customer customer = new Customer("Name1", "Surname2", "email");
        customerRepo.save(customer);
        Long id = customer.getId();

        BasketView basket = basketService.createBasket(id);

        long basketId = basket.getId();
        assertTrue(basketRepo.existsById(basketId));
        assertEquals(basketRepo.getById(basketId).getCustomer(), customer);
    }

    @Test
    void shouldCreateBasket() {
        Customer customer = customerRepo.save(new Customer("igor", "qwert", "31@mail.com"));
        BasketRequest basket = new BasketRequest(customer.getId());

        assertEquals(basket.getCustomerId(), customer.getId());
    }

    @Test
    void shouldAddBookToTheBasket() {
        Customer customer = customerRepo.save(new Customer("lala", "popopo", "trw@mail.com"));

        BookAuthor bookAuthor = bookAuthorRepo.save(new BookAuthor("authornametest", "surnamauthortest", 1010));
        Book book = bookRepo.save(new Book("book-0", 123, 1.2F, 1222,
                bookAuthor));

        Basket basket = basketRepo.save(new Basket(customer, Collections.emptyList()));

        BasketResponse basketResponse = basketService.addBook(new AddBookToBasketRequest(basket.getId(), book.getId()));

        Basket actualBasket = basketRepo.findById(basketResponse.getId()).get();

        assertEquals("book-0", actualBasket.getBookList().get(0).getBookName());
    }
}
