package com.example.Studentlibrary.controller;

import com.example.Studentlibrary.entity.BookEntity;
import com.example.Studentlibrary.entity.StudentEntity;
import com.example.Studentlibrary.exception.BookException;
import com.example.Studentlibrary.exception.BookNotFoundException;
import com.example.Studentlibrary.exception.StudentException;
import com.example.Studentlibrary.exception.StudentNotFoundExeption;
import com.example.Studentlibrary.service.BookServiceImpl;
import com.example.Studentlibrary.service.StudentServiceImp;
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

@RestController
@RequestMapping("/librarian")
public class LibrarianController {

    private final BookServiceImpl bookService;
    private final StudentServiceImp studentServiceImp;

    public LibrarianController(BookServiceImpl bookService, StudentServiceImp studentServiceImp) {
        this.bookService = bookService;
        this.studentServiceImp = studentServiceImp;
    }

    @GetMapping
    public ResponseEntity getLibrarian() {
        try {
            return ResponseEntity.ok("Сервер запущен");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PostMapping("/book") //добавляем новую книгу
    public ResponseEntity newBook(@RequestBody BookEntity bookInput) {
        try {
            bookService.bookNew(bookInput);
            return ResponseEntity.ok("Книга сохранена");
        } catch (BookException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/book") //информация о книге по id
    public ResponseEntity getOneBook(@RequestParam int id) {
        try {
            return ResponseEntity.ok(bookService.getOne(id));
        } catch (BookNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/book/{id}") //удаление книги по id
    public ResponseEntity deleteBook(@PathVariable int id) {
        try {
            return ResponseEntity.ok("Книга с id " + bookService.deleteBook(id) + " удалена!");
        } catch (BookNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PostMapping("/student") //добавляем нового студента
    public ResponseEntity newStudent(@RequestBody StudentEntity studentEntity) {
        try {
            studentServiceImp.studentNew(studentEntity);
            return ResponseEntity.ok("Студент прошел регистрацию");
        } catch (StudentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/student") //информация о студенте по id
    public ResponseEntity getOneStudent(@RequestParam int id) {
        try {
            return ResponseEntity.ok(studentServiceImp.getOne(id));
        } catch (StudentNotFoundExeption e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/student/{id}") //удаление студента по id
    public ResponseEntity deleteStudent(@PathVariable int id) {
        try {
            return ResponseEntity.ok("Студент с id " + studentServiceImp.deleteStudent(id) + " удален!");
        } catch (StudentNotFoundExeption e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PostMapping("/add")
    public ResponseEntity studentByBook(@RequestBody BookEntity book, @RequestParam int studentId) {
        try {
            return ResponseEntity.ok(bookService.bookAddStudent(book, studentId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping
    public ResponseEntity bookByLibrarian(@RequestParam int id) {
        try {
            return ResponseEntity.ok(bookService.bookByStudent(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
