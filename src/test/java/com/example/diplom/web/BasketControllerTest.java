package com.example.diplom.web;

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
import com.example.diplom.service.BasketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BasketControllerTest {

    @Autowired
    BasketService basketService;

    @Autowired
    BasketRepo basketRepo;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BookRepo bookRepo;

    @Autowired
    BookAuthorRepo bookAuthorRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void shouldAddBasketToCustomerById() throws Exception {
        Customer customer = new Customer("testName", "surname", "email");
        customerRepo.save(customer);
        long id = customer.getId();

        BasketRequest basketRequest = new BasketRequest(id);

        String content = mockMvc.perform(post("/basket/addBasket")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(basketRequest)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        BasketView basketView = objectMapper.readValue(content, BasketView.class);
        assertTrue(basketRepo.existsById(basketView.getId()));
    }

    @Test
    @Transactional
    void shouldFindBasketById() throws Exception {
        Book book = new Book("1001", 500, 14F, 1500,
                new BookAuthor("name", "surname", 1245));
        Basket basket = new Basket(new Customer("CustomerName", "CustomerSurname", "email"), new ArrayList<>());
        basket.getBookList().add(book);
        basketRepo.save(basket);
        long basketId = basket.getId();

        mockMvc.perform(get("/basket/" + basketId))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.books.[0].name", equalTo("1001")));
    }

    @Test
    void shouldAddBookToBasket() throws Exception {
        Customer customer = customerRepo.save(new Customer("max", "laptev", "laptev@mail.com"));
        BookAuthor bookAuthor = bookAuthorRepo.save(new BookAuthor("radagon", "marika", 1000));
        Book book = bookRepo.save(new Book("loyd", 123, 12.2F, 1222,
                bookAuthor));
        Basket basket = basketRepo.save(new Basket(customer, Collections.emptyList()));
        long basketId = basket.getId();
        long bookId = book.getId();
        AddBookToBasketRequest addBookToBasketRequest = new AddBookToBasketRequest(basketId, bookId);

        mockMvc.perform(post("/basket/addBook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addBookToBasketRequest)))
                .andExpect(status().isOk())
                .andDo(print());

        assertTrue(basketRepo.findById(basketId).orElseThrow().getBookList().contains(book));
    }
}