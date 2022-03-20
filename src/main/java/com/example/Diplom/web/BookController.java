package com.example.Diplom.web;

import com.example.Diplom.dto.request.addbook.BookRequest;
import com.example.Diplom.dto.response.BookResponse;
import com.example.Diplom.dto.response.addbook.AddBookResponse;
import com.example.Diplom.ent.Book;
import com.example.Diplom.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor

public class BookController {
    private final BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<AddBookResponse> addBook(@RequestBody BookRequest bookRequest){
        AddBookResponse bookResponse = bookService.addBook(bookRequest);

        return ResponseEntity.ok(bookResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> findByBookId(@PathVariable Long id){
        BookResponse bookResponse = bookService.findByBookId(id);

        return ResponseEntity.ok(bookResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable Long id){
        bookService.deleteBookById(id);

        return ResponseEntity.ok("book deleted");
    }
}
