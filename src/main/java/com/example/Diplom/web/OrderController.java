package com.example.Diplom.web;

import com.example.Diplom.dto.request.OrderRequest;
import com.example.Diplom.dto.response.OrderResponse;
import com.example.Diplom.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor

public class OrderController {
    private final OrderService orderService;

        @PostMapping("/addOrder")
        public ResponseEntity<OrderResponse> addOrder(@RequestBody OrderRequest orderRequest){
            OrderResponse orderResponse = orderService.addOrder(orderRequest);

        return ResponseEntity.ok(orderResponse);
        }


}
