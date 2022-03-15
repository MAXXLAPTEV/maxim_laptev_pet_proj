package com.example.Diplom.controllers;


import com.example.Diplom.dto.request.CustomerRequest;
import com.example.Diplom.dto.response.BookResponse;
import com.example.Diplom.dto.response.CustomerResponse;
import com.example.Diplom.ent.Customer;
import com.example.Diplom.repo.CustomerRepo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc

 class CustomerControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    CustomerRepo customerRepo;

    @AfterEach
    public void deleteAll() {
        customerRepo.deleteAll();
    }

    public Customer addCustomer(String name, String surname, String email) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setSurname(surname);
        customer.setEmail(email);

        customerRepo.save(customer);
        return customer;
    }


    @Test
    void testMustAddCustomer() throws Exception {
        List<Customer> customers = customerRepo.findAll();
        CustomerRequest newCustomer = new CustomerRequest("кори", "гроу", "coreygrow@gmail.com");
        assertEquals(0, customers.size());
        MockHttpServletRequestBuilder requestBuilder = post("/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newCustomer));
        ResultActions resultActions = this.mockMvc.perform(requestBuilder);
        String result = resultActions.andReturn().getResponse().getContentAsString();
        List<Customer> customers1 = customerRepo.findAll();

        assertNotNull(result);
        assertEquals(1, customers1.size());
    }

    @Test
    void testMustFindCustomerById() throws Exception{
        Customer customer = addCustomer("кори", "гроу", "coreygrow@gmail.com");
        ResultActions resultActions = this.mockMvc.perform(get("/customer/" + customer.getId()));

        resultActions.andExpect(status().isOk());
    }

    @Test
    void testMustDeleteCustomerById() throws Exception{
        Customer customer = addCustomer("кори", "гроу", "coreygrow@gmail.com");
        ResultActions resultActions = this.mockMvc.perform(delete("/customer/" + customer.getId()));
        List<Customer> customers = customerRepo.findAll();

        assertEquals(0,customers.size());
        resultActions.andExpect(status().isOk());

    }
}


