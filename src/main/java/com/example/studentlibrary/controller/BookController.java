package com.example.studentlibrary.controller;

import com.example.studentlibrary.dto.Book;
import com.example.studentlibrary.entity.BookEntity;
import com.example.studentlibrary.service.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/book")
public class BookController {

    private final BookServiceImpl bookService;

    @PostMapping("/add")
    public Book createBook(@RequestBody BookEntity bookInput) {
        Book book = bookService.createBook(bookInput);
        return book;

    }

    @GetMapping("/id")
    public Book getOneBook(@RequestParam Integer id) {
        Book book = bookService.getBookById(id);
        return book;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/studenttookbook")
    public Book addStudentBook(@RequestParam Integer bookId, @RequestParam Integer studentId) {
        return bookService.addBookStudent(bookId, studentId);
    }

    @PutMapping("/update")
    public Book upDateBook(@RequestParam Integer bookId, @RequestBody BookEntity bookEntity) {
        return bookService.upDateBook(bookId, bookEntity);
    }

}
