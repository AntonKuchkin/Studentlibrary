package com.example.studentlibrary.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibrarianController {
    @GetMapping("/home")
    public String homePage(){
     return "Hello";
    }
    @GetMapping("/librarian")
    public String pageForAuthenticatedUser(){
        return "librarian";
    }
}
