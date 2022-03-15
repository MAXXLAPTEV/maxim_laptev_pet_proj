package com.example.Diplom.controllers;

import com.example.Diplom.dto.request.BookRequest;
import com.example.Diplom.dto.response.BookResponse;
import com.example.Diplom.ent.Book;
import com.example.Diplom.repo.BookRepo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc

public class BookControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BookRepo bookRepo;

    @AfterEach
    public void deleteAll(){
        bookRepo.deleteAll();
    }


    public Book addBook(String authorName,String bookName, int pages, Float cost) {
        Book book = new Book();
        book.setBookName(bookName);
        book.setAuthorName(authorName);
        book.setPages(pages);
        book.setCost(cost);

        bookRepo.save(book);
        return book;
    }


    @Test
    public void testMustAddBook() throws Exception {
        List<Book> books = bookRepo.findAll();
        BookRequest newBook = new BookRequest("Анджей Сапковский", "последнее желание", 480, 1500f);
        assertEquals(0, books.size());
        MockHttpServletRequestBuilder requestBuilder = post("/book/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newBook));
        ResultActions resultActions = this.mockMvc.perform(requestBuilder);
        String result = resultActions.andReturn().getResponse().getContentAsString();
        List<Book> books1 = bookRepo.findAll();

        assertNotNull(result);
        assertEquals(1, books1.size());
    }

    @Test
    public void MustFindBookById() throws Exception{
        Book book = addBook("Анджей Сапковский", "последнее желание", 480, 1500f);
        ResultActions resultActions = this.mockMvc.perform(get("/book/" + book.getId()));

        resultActions.andExpect(status().isOk());

        }

    @Test
    public void MustDeleteBookById() throws Exception{
        Book book = addBook("Анджей Сапковский", "последнее желание", 480, 1500f);
        ResultActions resultActions = this.mockMvc.perform(delete("/book/" + book.getId()));
        List<Book> books = bookRepo.findAll();

        assertEquals(0, books.size());
        resultActions.andExpect(status().isOk());
    }
}



