package com.example.studentlibrary.controller;

import com.example.studentlibrary.dto.Book;
import com.example.studentlibrary.dto.Student;
import com.example.studentlibrary.entity.BookEntity;
import com.example.studentlibrary.service.BookServiceImpl;
import com.example.studentlibrary.service.StudentServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/book")
public class BookController {

    private final BookServiceImpl bookService;
    private final StudentServiceImp studentServiceImp;

    @PostMapping("/add")
    public Book createBook(@RequestBody BookEntity bookInput) {
        return bookService.createBook(bookInput);

    }

    @GetMapping("/id")
    public Book getOneBook(@RequestParam Integer id) {
        return bookService.getBookById(id);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
    }

    @PostMapping("/add-student-book")
    public Student addStudentBook(@RequestParam Integer bookId, @RequestParam Integer studentId) {
        return studentServiceImp.addBookStudent(bookId, studentId);
    }

    @PutMapping("/update")
    public Book updateBook(@RequestParam Integer bookId, @RequestBody BookEntity bookEntity) {
        return bookService.updateBook(bookId, bookEntity);
    }

}
