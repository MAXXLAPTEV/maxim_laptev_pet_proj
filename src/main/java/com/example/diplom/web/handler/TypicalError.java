package com.example.diplom.web.handler;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.http.HttpStatus;

public enum TypicalError {

    UNKNOWN(10, "Unknown exception",HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(11, "Bad request", HttpStatus.BAD_REQUEST),
    NOT_FOUND(12, "Can't find", HttpStatus.NOT_FOUND);


    private final int code;
    private final String message;
    private final HttpStatus status;

    TypicalError(int code, String message, HttpStatus status) {
        this.message = message;
        this.code = code;
        this.status = status;
    }

    @JsonValue
    public int code() {
        return code;
    }
    @JsonValue
    public String getMessage() {
        return message;
    }
    @JsonValue
    public HttpStatus getStatus() {
        return status;
    }

}
