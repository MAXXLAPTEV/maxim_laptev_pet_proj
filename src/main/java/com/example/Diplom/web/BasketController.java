package com.example.Diplom.web;

import com.example.Diplom.dto.request.BasketRequest;
import com.example.Diplom.dto.response.BasketResponse;
import com.example.Diplom.dto.response.CustomerResponse;
import com.example.Diplom.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor

public class BasketController {
    private final BasketService basketService;


    @PostMapping("/addBook")
    public ResponseEntity<BasketResponse> addBookToBasket(@RequestBody BasketRequest basketRequest){
        BasketResponse basketResponse = basketService.addBook(basketRequest);

        return ResponseEntity.ok(basketResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBasket(@RequestBody BasketRequest basketRequest) {
        basketService.deleteBasket(basketRequest);

        return ResponseEntity.ok("Basket deleted");
    }
}
