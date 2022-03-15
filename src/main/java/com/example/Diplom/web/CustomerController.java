package com.example.Diplom.web;

import com.example.Diplom.dto.request.CustomerRequest;
import com.example.Diplom.dto.response.CustomerResponse;
import com.example.Diplom.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/customer")
@RequiredArgsConstructor

public class CustomerController {
    private final CustomerService customerService;


    @PostMapping("/add")
    public ResponseEntity<CustomerResponse> addCustomer(@RequestBody CustomerRequest customerRequest){
        CustomerResponse customerResponse = customerService.addCustomer(customerRequest);

        return ResponseEntity.ok(customerResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findCustomerById(@PathVariable Long id){
        CustomerResponse customerResponse = customerService.findByCustomerId(id);

        return ResponseEntity.ok(customerResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable Long id){
        customerService.deleteCustomerById(id);

        return ResponseEntity.ok("deleted");
    }
}
