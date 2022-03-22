package com.example.Diplom.dto.request;

import lombok.Data;

@Data
public class AddBookToBasketRequest {
    public Long basketId;
    public Long bookId;

    public AddBookToBasketRequest(Long basketId, Long bookId) {
        this.basketId = basketId;
        this.bookId = bookId;
    }
}


