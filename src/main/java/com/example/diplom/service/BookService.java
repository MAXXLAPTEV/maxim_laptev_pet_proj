package com.example.diplom.service;

import com.example.diplom.dto.request.addbook.BookRequest;
import com.example.diplom.dto.response.BookResponse;
import com.example.diplom.dto.response.addbook.AddBookResponse;
import com.example.diplom.dto.response.addbook.BookAuthorView;
import com.example.diplom.dto.response.addbook.BookView;
import com.example.diplom.ent.Book;
import com.example.diplom.ent.BookAuthor;
import com.example.diplom.repo.BookAuthorRepo;
import com.example.diplom.repo.BookRepo;
import com.example.diplom.web.handler.ServiceException;
import com.example.diplom.web.handler.TypicalError;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepo bookRepo;
    private final BookAuthorRepo bookAuthorRepo;
    private final ObjectMapper objectMapper;

    public AddBookResponse addBook(BookRequest bookRequest) {
        Optional<BookAuthor> optionalBookAuthor =
                findBookAuthorByName(bookRequest.getBookAuthor().getAuthorName(), bookRequest.getBookAuthor().getAuthorSurname());

        if (!optionalBookAuthor.isPresent()) {
            BookAuthor newAuthor = new BookAuthor(bookRequest.getBookAuthor());
            bookAuthorRepo.save(newAuthor);
            Book book = bookRepo.save(new Book(bookRequest.getBook(), newAuthor));
            return new AddBookResponse(new BookView(book.getId(), book.getBookName()), new BookAuthorView(book.getBookAuthor().getId(), true));
        } else {
            Book book = bookRepo.save(new Book(bookRequest.getBook(), optionalBookAuthor.get()));
            return new AddBookResponse(new BookView(book.getId(), book.getBookName()), new BookAuthorView(book.getBookAuthor().getId(), false));
        }
    }

    private Optional<BookAuthor> findBookAuthorByName(String authorName, String authorSurname) {
        return bookAuthorRepo.findByAuthorNameEqualsAndAuthorSurnameEquals(authorName, authorSurname);
    }

    public BookResponse findByBookId(Long id) {
        Book book = bookRepo.findById(id).orElseThrow(() ->
                new ServiceException("No such book", TypicalError.NOT_FOUND));
        BookResponse response = objectMapper.convertValue(book, BookResponse.class);
        return response;
    }

    public void deleteBookById(Long id) {
        bookRepo.deleteById(id);
    }


}
