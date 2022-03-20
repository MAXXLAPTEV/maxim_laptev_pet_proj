package com.example.Diplom.controllers;

import com.example.Diplom.ent.Book;
import com.example.Diplom.ent.BookAuthor;
import com.example.Diplom.repo.BookAuthorRepo;
import com.example.Diplom.repo.BookRepo;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc

public class BookControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BookRepo bookRepo;

    @Autowired
    BookAuthorRepo bookAuthorRepo;

    @AfterEach
    public void deleteAll(){
        bookRepo.deleteAll();
    }

//    //TODO add testing for addBook, addAuthorForBook, findBook, removeBook
//
//
//    public Book addBook(String bookName, int pages, Float cost, int dateOfPrinting){
//        Book book = new Book();
//        book.setBookName(bookName);
//        book.setCost(cost);
//        book.setPages(pages);
//        book.setBookAuthor(new BookAuthor());
//        book.setDateOfPrinting(dateOfPrinting);
//        bookRepo.save(book);
//        return book;
//    }

//    public BookAuthor addBookAuthor(String authorName, String authorSurname, int authorBirth ){
//        BookAuthor bookAuthor = new BookAuthor();
//        bookAuthor.setAuthorName(authorName);
//        bookAuthor.setAuthorSurname(authorSurname);
//        bookAuthor.setAuthorBirth(authorBirth);
//        bookAuthorRepo.save(bookAuthor);
//        return bookAuthor;
//    }



//        @Test
//        public void testMustAddBook() throws Exception {
//        List<Book> books = bookRepo.findAll();
//        BookRequest newBook = new BookRequest("последнее желание", 1500, 480f, 2012);
//        assertEquals(0, books.size());
//            MockHttpServletRequestBuilder requestBuilder = post("/book/add")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(newBook));
//            ResultActions resultActions = this.mockMvc.perform(requestBuilder);
//        String result = resultActions.andReturn().getResponse().getContentAsString();
//        List<Book> books1 = bookRepo.findAll();
//
//        assertNotNull(result);
//        assertEquals(1, books1.size());
//        }


//    @Test
//    public void testMustAddBook() throws Exception {
//        List<Book> books = bookRepo.findAll();
//        BookRequest newBook = new BookRequest("Анджей Сапковский", "последнее желание", 480, 1500f);
//        assertEquals(0, books.size());
//        MockHttpServletRequestBuilder requestBuilder = post("/book/add")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(newBook));
//        ResultActions resultActions = this.mockMvc.perform(requestBuilder);
//        String result = resultActions.andReturn().getResponse().getContentAsString();
//        List<Book> books1 = bookRepo.findAll();
//
//        assertNotNull(result);
//        assertEquals(1, books1.size());
//    }
//
//    @Test
//    public void MustFindBookById() throws Exception{
//        Book book = addBook("последнее желание", 480, 1500f, 1200);
//        ResultActions resultActions = this.mockMvc.perform(get("/book/" + book.getId()));
//
//        resultActions.andExpect(status().isOk());
//
//        }
////
////    public Book addBook(String authorName,String bookName, int pages, Float cost) {
////        Book book = new Book();
////        book.setBookName(bookName);
////        book.setAuthorName(authorName);
////        book.setPages(pages);
////        book.setCost(cost);
////
////        bookRepo.save(book);
////        return book;
////    }
//    @Test
//    public void MustDeleteBookById() throws Exception{
//        Book book = addBook("Анджей Сапковский", 22, 480f, 1500);
//        ResultActions resultActions = this.mockMvc.perform(delete("/book/" + book.getId()));
//        List<Book> books = bookRepo.findAll();
//
//        assertEquals(0, books.size());
//        resultActions.andExpect(status().isOk());
//    }
}



