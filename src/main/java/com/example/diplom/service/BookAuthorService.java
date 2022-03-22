package com.example.diplom.service;


import com.example.diplom.dto.request.BookAuthorRequest;
import com.example.diplom.dto.response.BookAuthorResponse;
import com.example.diplom.ent.BookAuthor;
import com.example.diplom.repo.BookAuthorRepo;
import com.example.diplom.web.handler.ServiceException;
import com.example.diplom.web.handler.TypicalError;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BookAuthorService {
    private final BookAuthorRepo bookAuthorRepo;
    private final ObjectMapper objectMapper;

    public BookAuthorResponse addBookAuthor(BookAuthorRequest bookAuthorRequest) {
        BookAuthor bookAuthor = objectMapper.convertValue(bookAuthorRequest, BookAuthor.class);
        bookAuthorRepo.save(bookAuthor);
        return objectMapper.convertValue(bookAuthor, BookAuthorResponse.class);

    }

    public BookAuthorResponse findBookAuthorById(long id) {
        BookAuthor bookAuthor = bookAuthorRepo.findById(id).orElseThrow(() ->
                new ServiceException("no such author", TypicalError.NOT_FOUND));
        return objectMapper.convertValue(bookAuthor, BookAuthorResponse.class);
    }

    public void deleteAuthor(Long id) {
        bookAuthorRepo.deleteById(id);
    }
}
