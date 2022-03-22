package com.example.Diplom.service;

import com.example.Diplom.dto.request.CheckoutRequest;
import com.example.Diplom.dto.request.OrderRequest;
import com.example.Diplom.dto.response.BasketResponse;
import com.example.Diplom.dto.response.CheckoutResponse;
import com.example.Diplom.dto.response.OrderResponse;
import com.example.Diplom.ent.Basket;
import com.example.Diplom.ent.Book;
import com.example.Diplom.ent.Order;
import com.example.Diplom.repo.BasketRepo;
import com.example.Diplom.repo.OrderRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

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
        BigDecimal decimal = new BigDecimal(sum).setScale(2, RoundingMode.HALF_UP);

        return new CheckoutResponse(decimal.doubleValue());
    }
}
