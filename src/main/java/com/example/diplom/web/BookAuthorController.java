package com.example.diplom.web;


import com.example.diplom.dto.request.BookAuthorRequest;
import com.example.diplom.dto.response.BookAuthorResponse;
import com.example.diplom.service.BookAuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class BookAuthorController {
    private final BookAuthorService bookAuthorService;

    @PostMapping("/add")
    public ResponseEntity<BookAuthorResponse> addAuthor(@RequestBody BookAuthorRequest bookAuthorRequest){
        BookAuthorResponse bookAuthorResponse = bookAuthorService.addBookAuthor(bookAuthorRequest);

        return ResponseEntity.ok(bookAuthorResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookAuthorResponse> findAuthorById(@PathVariable Long id){
        BookAuthorResponse bookAuthorResponse = bookAuthorService.findBookAuthorById(id);

        return ResponseEntity.ok(bookAuthorResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthorById(@PathVariable Long id){
        bookAuthorService.deleteAuthor(id);

        return ResponseEntity.ok("author deleted");
    }
}
