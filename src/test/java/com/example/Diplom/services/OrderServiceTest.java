package com.example.Diplom.services;


import com.example.Diplom.ent.Book;
import com.example.Diplom.ent.Customer;
import com.example.Diplom.repo.BasketRepo;
import com.example.Diplom.repo.BookRepo;
import com.example.Diplom.repo.CustomerRepo;
import com.example.Diplom.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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

    public Book addBook(){
        Book book = new Book();
        book.setCost(1500f);
        book.setBookName("java for all");
        book.setDateOfPrinting(1290);
        book.setPages(10000);
        bookRepo.save(book);

        return book;
    }

    public Customer addCustomer(){
        Customer customer = new Customer();
        customer.setEmail("zdfrfhg@gmail.com");
        customer.setName("max");
        customer.setSurname("notmax");
        customerRepo.save(customer);

        return customer;
    }




}
