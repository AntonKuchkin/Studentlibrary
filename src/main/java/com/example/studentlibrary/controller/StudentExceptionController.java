package com.example.studentlibrary.controller;

import com.example.studentlibrary.exception.StudentIncorrect;
import com.example.studentlibrary.exception.StudentNotFoundExeption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentExceptionController {
    @ExceptionHandler
    public ResponseEntity<StudentIncorrect> handleException(StudentNotFoundExeption exception) {
        StudentIncorrect studentIncorrect = new StudentIncorrect();
        studentIncorrect.setInfoStudent(exception.getMessage());
        return new ResponseEntity<>(studentIncorrect, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<StudentIncorrect> handleException(Exception exception) {
        StudentIncorrect studentIncorrect = new StudentIncorrect();
        studentIncorrect.setInfoStudent(exception.getMessage());
        return new ResponseEntity<>(studentIncorrect, HttpStatus.BAD_REQUEST);
    }
}
