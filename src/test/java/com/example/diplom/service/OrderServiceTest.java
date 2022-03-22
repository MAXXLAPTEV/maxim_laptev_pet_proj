package com.example.diplom.service;



import com.example.diplom.dto.request.CheckoutRequest;
import com.example.diplom.dto.request.OrderRequest;

import com.example.diplom.dto.response.CheckoutResponse;
import com.example.diplom.dto.response.OrderResponse;
import com.example.diplom.ent.*;
import com.example.diplom.repo.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    BookRepo bookRepo;

    @Autowired
    BasketRepo basketRepo;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    BookAuthorRepo bookAuthorRepo;

    @Autowired
    BasketService basketService;


    @Test
    void shouldAddOrder(){
        Customer customer = customerRepo.save(new Customer("max", "laptev", "laptev@mail.com"));

        BookAuthor bookAuthor = bookAuthorRepo.save(new BookAuthor( "radagon", "marika", 1000));
        Book book = bookRepo.save(new Book("loyd", 123, 12.2F ,1222,
                bookAuthor));

        List<Book> books = new ArrayList<>();
        books.add(book);

        Basket basket = basketRepo.save(new Basket(customer, books));

        OrderResponse orderResponse = orderService.addOrder(new OrderRequest(customer.getId()));

        Optional<Order> order = orderRepo.findById(orderResponse.getId());

        assertEquals(true, order.isPresent());
    }

    @Test
    void shouldCheckout(){
        Customer customer = customerRepo.save(new Customer("max", "laptev", "laptev@mail.com"));

        BookAuthor bookAuthor = bookAuthorRepo.save(new BookAuthor( "radagon", "marika", 1000));
        Book book = bookRepo.save(new Book("loyd", 123, 12.2F ,1222,
                bookAuthor));
        Book book1 = bookRepo.save(new Book("pink",234,23.3F,1333,bookAuthor));

        List<Book> books = new ArrayList<>();
        books.add(book);
        books.add(book1);

        Basket basket = basketRepo.save(new Basket(customer, books));
        Order order = orderRepo.save(new Order(customer, basket));

        CheckoutResponse checkout = orderService.checkout(new CheckoutRequest(order.getId()));

        assertEquals(35.5, checkout.sum);


    }

}




