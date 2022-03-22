package com.example.diplom.service;

import com.example.diplom.dto.request.CheckoutRequest;
import com.example.diplom.dto.request.OrderRequest;
import com.example.diplom.dto.response.CheckoutResponse;
import com.example.diplom.dto.response.OrderResponse;
import com.example.diplom.ent.Basket;
import com.example.diplom.ent.Book;
import com.example.diplom.ent.Order;
import com.example.diplom.repo.BasketRepo;
import com.example.diplom.repo.OrderRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final BasketRepo basketRepo;
    private final OrderRepo orderRepo;
    private final ObjectMapper objectMapper;


    private Basket findBasket(OrderRequest orderRequest) {
        return basketRepo.findByCustomerId(orderRequest.getCustomerId());
    }

    public OrderResponse addOrder(OrderRequest orderRequest){
        Basket basket = findBasket(orderRequest);
        Order order = new Order();
        order.setCustomer(basket.getCustomer());
        order.setBasket(basket);
        orderRepo.save(order);
        return objectMapper.convertValue(order,OrderResponse.class);
    }

    public CheckoutResponse checkout(CheckoutRequest checkoutRequest) {
        Optional<Order> order = orderRepo.findById(checkoutRequest.getOrderId());
        Basket basket = order.get().getBasket();
        List<Book> books = basket.getBookList();
        Double sum = books.stream().mapToDouble(Book::getCost).sum();
        BigDecimal decimal = BigDecimal.valueOf(sum).setScale(2, RoundingMode.HALF_UP);

        return new CheckoutResponse(decimal.doubleValue());
    }
}
