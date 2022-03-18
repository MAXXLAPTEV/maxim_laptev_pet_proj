package com.example.Diplom.service;

import com.example.Diplom.dto.request.CustomerRequest;
import com.example.Diplom.dto.response.CustomerResponse;
import com.example.Diplom.ent.Customer;
import com.example.Diplom.repo.CustomerRepo;
import com.example.Diplom.web.handler.ServiceException;
import com.example.Diplom.web.handler.TypicalError;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<CustomerResponse> getAllCustomers(Pageable pageable) {
        List<CustomerResponse> customers = new ArrayList<>();
        for(Customer customer : customerRepo.findAll(pageable).getContent()) {
            customers.add(objectMapper.convertValue(customer, CustomerResponse.class));
        }
        return customers;
    }
    public void deleteCustomerById(Long id) {
        customerRepo.deleteById(id);
    }
}
