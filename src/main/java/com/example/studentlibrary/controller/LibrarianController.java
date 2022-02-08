package com.example.studentlibrary.controller;

import com.example.studentlibrary.entity.BookEntity;
import com.example.studentlibrary.entity.StudentEntity;
import com.example.studentlibrary.exception.BookException;
import com.example.studentlibrary.exception.BookNotFoundException;
import com.example.studentlibrary.exception.StudentException;
import com.example.studentlibrary.exception.StudentNotFoundExeption;
import com.example.studentlibrary.service.BookServiceImpl;
import com.example.studentlibrary.service.StudentServiceImp;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/librarian") // Почему библиотекарь? А не библиотека? //Изначально хотел сделать библиотекаря который заводит и студентов и книги в базу
public class LibrarianController {

    private final BookServiceImpl bookService;
    private final StudentServiceImp studentServiceImp;

    @PostMapping("/book") //добавляем новую книгу
    public ResponseEntity createBook(@RequestBody BookEntity bookInput) {
        try {
            bookService.createBook(bookInput);
            return ResponseEntity.ok("Книга сохранена");
        } catch (BookException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/book") //информация о книге по id
    public ResponseEntity getOneBook(@RequestParam Integer id) {
        try {
            return ResponseEntity.ok(bookService.getBookById(id));
        } catch (BookNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/book/{id}") //удаление книги по id
    public ResponseEntity deleteBook(@PathVariable Integer id) {
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
            studentServiceImp.createStudent(studentEntity);
            return ResponseEntity.ok("Студент прошел регистрацию");
        } catch (StudentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/student") //информация о студенте по id
    public ResponseEntity getOneStudent(@RequestParam Integer id) {
        try {
            return ResponseEntity.ok(studentServiceImp.getStudentById(id));
        } catch (StudentNotFoundExeption e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/student/{id}") //удаление студента по id
    public ResponseEntity deleteStudent(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok("Студент с id " + studentServiceImp.deleteStudent(id) + " удален!");
        } catch (StudentNotFoundExeption e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PostMapping("/add")
    public ResponseEntity addStudentBook (@RequestBody BookEntity book, @RequestParam Integer studentId) {
        try {
            return ResponseEntity.ok(bookService.addBookStudent(book, studentId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping
    public ResponseEntity bookByLibrarian(@RequestParam Integer id) {
        try {
            return ResponseEntity.ok(bookService.bookByStudent(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
