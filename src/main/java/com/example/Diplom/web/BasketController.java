package com.example.Diplom.web;

import com.example.Diplom.dto.request.AddBookToBasketRequest;
import com.example.Diplom.dto.request.BasketRequest;
import com.example.Diplom.dto.request.CustomerRequest;
import com.example.Diplom.dto.response.BasketResponse;
import com.example.Diplom.dto.response.CustomerResponse;
import com.example.Diplom.dto.response.addbasket.BasketView;
import com.example.Diplom.ent.Basket;
import com.example.Diplom.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor

public class BasketController {
    private final BasketService basketService;

    @PostMapping("/addBasket")
    public ResponseEntity<BasketView> addBasket(@RequestBody BasketRequest basketRequest){
        BasketView basket = basketService.createBasket(basketRequest.getCustomerId());
        return ResponseEntity.ok(basket);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BasketView> findBasketById(@PathVariable Long id){
        BasketView basket = basketService.findBasket(id);
        return ResponseEntity.ok(basket);
    }


    @PostMapping("/addBook")
    public ResponseEntity<BasketResponse> addBookToBasket(@RequestBody AddBookToBasketRequest addBookToBasketRequest){
        BasketResponse basketResponse = basketService.addBook(addBookToBasketRequest);

        return ResponseEntity.ok(basketResponse);
    }

}
