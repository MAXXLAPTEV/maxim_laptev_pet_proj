package com.example.Diplom.web.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Slf4j
@ControllerAdvice
public class ExHandler extends ResponseEntityExceptionHandler {
// FIXME: 16.03.2022 add custom exception processing

    @ExceptionHandler(value
            = {ServiceException.class})
    protected ResponseEntity<ErrorMessage> handleConflict(ServiceException e) {
        log.error(e.getMessage());
        TypicalError typicalError = e.getTypicalError();
        return new ResponseEntity<>(new ErrorMessage(-1, typicalError.name()), typicalError.getStatus());
    }

    @ExceptionHandler(value
            = {Throwable.class})
    protected ResponseEntity<ErrorMessage> handleConflict(Throwable e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new ErrorMessage(-1, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}