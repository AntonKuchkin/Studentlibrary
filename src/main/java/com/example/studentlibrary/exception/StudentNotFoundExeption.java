package com.example.studentlibrary.exception;

public class StudentNotFoundExeption extends RuntimeException {
    public StudentNotFoundExeption(String message) {
        super(message);
    }
}
