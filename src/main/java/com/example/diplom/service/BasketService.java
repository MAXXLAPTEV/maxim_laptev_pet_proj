package com.example.diplom.service;

import com.example.diplom.dto.request.AddBookToBasketRequest;
import com.example.diplom.dto.response.BasketResponse;
import com.example.diplom.dto.response.addbasket.BasketView;
import com.example.diplom.ent.Basket;
import com.example.diplom.ent.Book;
import com.example.diplom.repo.BasketRepo;
import com.example.diplom.repo.BookRepo;
import com.example.diplom.repo.CustomerRepo;
import com.example.diplom.web.handler.ServiceException;
import com.example.diplom.web.handler.TypicalError;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketRepo basketRepo;
    private final CustomerRepo customerRepo;
    private final ObjectMapper objectMapper;
    private final BookRepo bookRepo;


    public BasketView findBasket(Long id) {
        Optional<Basket> basket = basketRepo.findById(id);
        return new BasketView(basket.get().getId(), basket.get().getBookList());
    }

    public BasketView createBasket(Long id) {
        Basket newBasket = new Basket();
        newBasket.setCustomer(customerRepo.getById(id));
        basketRepo.save(newBasket);
        return new BasketView(newBasket.getId());
    }

    public BasketResponse addBook(AddBookToBasketRequest addBookToBasketRequest) {
        Optional<Basket> basket = basketRepo.findById(addBookToBasketRequest.basketId);
        List<Book> bookList = basket.get().getBookList();
        Optional<Book> book = bookRepo.findById(addBookToBasketRequest.bookId);
        bookList.add(book.get());
        basket.get().setBookList(bookList);
        basketRepo.save(basket.get());
        return objectMapper.convertValue(basket, BasketResponse.class);
    }


}


