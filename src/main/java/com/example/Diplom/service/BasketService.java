package com.example.Diplom.service;

import com.example.Diplom.dto.request.AddBookToBasketRequest;
import com.example.Diplom.dto.request.BasketRequest;
import com.example.Diplom.dto.response.BasketResponse;
import com.example.Diplom.dto.response.addbasket.BasketView;
import com.example.Diplom.ent.Basket;
import com.example.Diplom.ent.Book;
import com.example.Diplom.ent.Customer;
import com.example.Diplom.repo.BasketRepo;
import com.example.Diplom.repo.BookRepo;
import com.example.Diplom.repo.CustomerRepo;
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
        return new BasketView(basket.get().getId(),basket.get().getBookList());
    }


    public Customer findCustomer(BasketRequest basketRequest) {
        return customerRepo.getById(basketRequest.getCustomerId());
    }

//    private Basket findBook(BasketRequest basketRequest) {
//        return basketRepo.getById(basketRequest.getBookId());
//    }

//    public BasketResponse deleteBasket(BasketRequest basketRequest){
//        Customer customer = findCustomer(basketRequest);
//        Basket basket = findBasket(id);
//        basketRepo.delete(basket);
//        return null;
//    }

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


