package com.example.studentlibrary.controller;

import com.example.studentlibrary.exception.BookIncorrect;
import com.example.studentlibrary.exception.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookExceptionController {
    @ExceptionHandler
    public ResponseEntity<BookIncorrect> handleException(BookNotFoundException exception) {
        BookIncorrect bookIncorrect = new BookIncorrect();
        bookIncorrect.setInfo(exception.getMessage());
        return new ResponseEntity<>(bookIncorrect, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<BookIncorrect> handleException(Exception exception) {
        BookIncorrect bookIncorrect = new BookIncorrect();
        bookIncorrect.setInfo(exception.getMessage());
        return new ResponseEntity<>(bookIncorrect, HttpStatus.BAD_REQUEST);
    }
}
