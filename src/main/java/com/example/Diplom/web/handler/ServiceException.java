package com.example.Diplom.web.handler;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {

    private final TypicalError typicalError;

    public ServiceException(String message, TypicalError typicalError) {
        super(message);
        this.typicalError = typicalError;
    }

}
