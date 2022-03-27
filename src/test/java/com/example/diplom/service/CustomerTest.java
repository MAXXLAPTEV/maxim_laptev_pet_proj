package com.example.diplom.service;

import com.example.diplom.dto.response.CustomerResponse;
import com.example.diplom.ent.Customer;
import com.example.diplom.repo.CustomerRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CustomerTest {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    CustomerService customerService;



    @Test
    void shouldFindCustomerById(){
        Customer customer = customerRepo.save(new Customer("max", "laptev", "maxlaptev@gmail.com"));

        CustomerResponse customerId = customerService.findByCustomerId(customer.getId());

        assertEquals(customer.getId(), customerId.getId());
        assertEquals("max", customerId.getName());
        assertEquals("laptev", customerId.getSurname());
    }

    @Test
    void shouldDeleteCustomerById(){
        Customer customer = customerRepo.save(new Customer("max", "laptev", "maxlaptev@gmail.com"));
        customerService.deleteCustomerById(customer.getId());
        Optional<Customer> customer1 = customerRepo.findById(customer.getId());

        assertEquals(false, customer1.isPresent());

    }

}
