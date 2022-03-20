package com.example.Diplom.service;


import com.example.Diplom.dto.request.BookAuthorRequest;
import com.example.Diplom.dto.response.BookAuthorResponse;
import com.example.Diplom.ent.BookAuthor;
import com.example.Diplom.repo.BookAuthorRepo;
import com.example.Diplom.web.handler.ServiceException;
import com.example.Diplom.web.handler.TypicalError;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BookAuthorService {
    private final BookAuthorRepo bookAuthorRepo;
    private final ObjectMapper objectMapper;

    public BookAuthorResponse addBookAuthor(BookAuthorRequest bookAuthorRequest){
        BookAuthor bookAuthor = objectMapper.convertValue(bookAuthorRequest, BookAuthor.class);
        bookAuthorRepo.save(bookAuthor);
        return objectMapper.convertValue(bookAuthor, BookAuthorResponse.class);

    }

    public BookAuthorResponse findBookAuthorById(long id){
        BookAuthor bookAuthor = bookAuthorRepo.findById(id).orElseThrow(() ->
                new ServiceException("no such author", TypicalError.NOT_FOUND));
        return objectMapper.convertValue(bookAuthor, BookAuthorResponse.class);
    }

    public List<BookAuthorResponse> getAllAuthors(Pageable pageable){
        List<BookAuthorResponse> bookAuthorResponses = new ArrayList<>();
        for (BookAuthor bookAuthor : bookAuthorRepo.findAll(pageable)) {
            bookAuthorResponses.add(objectMapper.convertValue(bookAuthor, BookAuthorResponse.class));
        }
        return bookAuthorResponses;
    }

    public void deleteAuthor(Long  id){bookAuthorRepo.deleteById(id);}
}
