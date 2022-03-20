//package com.example.Diplom.services;
//
//
//import com.example.Diplom.dto.request.addbook.BookRequest;
//import com.example.Diplom.dto.response.BookResponse;
//import com.example.Diplom.ent.Basket;
//import com.example.Diplom.ent.Book;
//import com.example.Diplom.ent.BookAuthor;
//import com.example.Diplom.repo.BookAuthorRepo;
//import com.example.Diplom.repo.BookRepo;
//import com.example.Diplom.service.BookService;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//public class BookServiceTest {
//
//    @Autowired
//    BookRepo bookRepo;
//
//    @Autowired
//    BookService bookService;
//
//    @AfterEach
//    void resetDB() {
//        bookRepo.deleteAll();
//    }
//
//    public Book addBook(String bookName, int pages, Float cost, int dateOfPrinting) {
//        Book book = new Book();
//        book.setBookName(bookName);
//        book.setPages(pages);
//        book.setCost(cost);
//        book.setDateOfPrinting(dateOfPrinting);
////        book.setBookAuthor(new BookAuthor());
////        book.setBasket(new Basket());
////        bookRepo.save(book);
////        return book;
////    }
////
////    @Transactional
////    @Test
////    public void mustAddBook() {
////        BookRequest newBook = new BookRequest("last wish", 408, 1500f, 2013);
////        List<Book> list = bookRepo.findAll();
////        assertEquals(0, list.size());
////        BookResponse bookResponse = bookService.addBook(newBook);
////        assertEquals(newBook, bookResponse);
//
////        ProductRequest newProduct = new ProductRequest(Brand.THULE, Type.BUSINESS, 15, 5, 4500.90f);
////        List<Product> list = productRepository.findAll();
////        assertEquals(0, list.size());
////        ProductResponse testProduct = productService.create(newProduct);
////
////        assertProductEquals(newProduct, testProduct);
////
////        List<Product> newList = productRepository.findAll();
////        int fistSize = list.size() + 1;
////        assertEquals(fistSize, newList.size());
//    }
//
//
//
//
//
//
//}
