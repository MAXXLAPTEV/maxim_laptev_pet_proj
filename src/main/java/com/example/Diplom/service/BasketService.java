package com.example.Diplom.service;

import com.example.Diplom.dto.request.BasketRequest;
import com.example.Diplom.dto.response.BasketResponse;
import com.example.Diplom.ent.Basket;
import com.example.Diplom.ent.Customer;
import com.example.Diplom.repo.BasketRepo;
import com.example.Diplom.repo.CustomerRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketRepo basketRepo;
    private final CustomerRepo customerRepo;
    private final ObjectMapper objectMapper;


    private Basket findBasket(Customer customer) {
        return basketRepo.findByCustomerId(customer.getId());
    }

    private Customer findCustomer(BasketRequest basketRequest) {
        return customerRepo.getById(basketRequest.getCustomerId());
    }

    private Basket findBook(BasketRequest basketRequest) {
        return basketRepo.getById(basketRequest.getBookId());
    }

    public BasketResponse deleteBasket(BasketRequest basketRequest){
        Customer customer = findCustomer(basketRequest);
        Basket basket = findBasket(customer);
        basketRepo.delete(basket);
        return null;
    }

    private Basket CreateBasket(Customer customer) {
            Basket basket = null;
            Basket newBasket = new Basket();
            newBasket.setCustomer(customer);
            basketRepo.save(newBasket);
            basket = newBasket;
            return basket;
        }


    public BasketResponse addBook(BasketRequest basketRequest) {
        Customer customer = findCustomer(basketRequest);
        Basket book = findBook(basketRequest);
        Basket basket = CreateBasket(customer);
        return objectMapper.convertValue(basket, BasketResponse.class);
    }


}
