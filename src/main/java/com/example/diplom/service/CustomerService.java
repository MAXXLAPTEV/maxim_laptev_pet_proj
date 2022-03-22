package com.example.diplom.service;

import com.example.diplom.dto.request.CustomerRequest;
import com.example.diplom.dto.response.CustomerResponse;
import com.example.diplom.ent.Customer;
import com.example.diplom.repo.CustomerRepo;
import com.example.diplom.web.handler.ServiceException;
import com.example.diplom.web.handler.TypicalError;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    public final CustomerRepo customerRepo;
    public final ObjectMapper objectMapper;

    public CustomerResponse addCustomer(CustomerRequest customerRequest) {
        Customer customer = objectMapper.convertValue(customerRequest, Customer.class);
        customerRepo.save(customer);
        return objectMapper.convertValue(customer, CustomerResponse.class);
    }

    public CustomerResponse findByCustomerId(Long id) {
        Customer customer = customerRepo.findById(id).orElseThrow(() ->
                new ServiceException("no such customer", TypicalError.NOT_FOUND));
        return objectMapper.convertValue(customer, CustomerResponse.class);
    }

    public void deleteCustomerById(Long id) {
        customerRepo.deleteById(id);
    }
}
