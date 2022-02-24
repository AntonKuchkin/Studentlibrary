package com.example.studentlibrary.service;

import com.example.studentlibrary.dto.Book;
import com.example.studentlibrary.entity.BookEntity;
import com.example.studentlibrary.entity.StudentEntity;
import com.example.studentlibrary.exception.BookNotFoundException;
import com.example.studentlibrary.exception.StudentNotFoundExeption;
import com.example.studentlibrary.repository.BookRepo;
import com.example.studentlibrary.repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepo bookRepo;
    private final StudentRepo studentRepo;

    public Book createBook(BookEntity bookEntity) {

        if (bookRepo.existsBookEntitiesByTitle(bookEntity.getTitle())) {
            throw new BookNotFoundException("Книга существует");
        } else {
            bookRepo.save(bookEntity);
            return Book.toModel(bookEntity);
        }
    }

    public Book getBookById(int bookId) {
        bookRepo.findById(bookId).orElseThrow(() -> new BookNotFoundException("Книга с id=" + bookId + " не существует"));
        BookEntity book = bookRepo.findById(bookId).get();
        return Book.toModel(book);
    }

    public void deleteBook(int bookId) {
        bookRepo.findById(bookId).orElseThrow(() -> new BookNotFoundException("Книга с id=" + bookId + " не существует"));
        bookRepo.deleteById(bookId);
    }

    public Book addBookStudent(int bookId, int studentId) {
        bookRepo.findById(bookId).orElseThrow(() -> new BookNotFoundException("Книга с id=" + bookId + " не существует"));
        studentRepo.findById(studentId).orElseThrow(() -> new StudentNotFoundExeption("Студента с id=" + studentId + " не существует"));
        StudentEntity student = studentRepo.findById(studentId).get();
        BookEntity bookEntity = bookRepo.findById(bookId).get();
        bookEntity.setStudent(student);
        bookRepo.save(bookEntity);
        return Book.toModel(bookEntity);
    }

    public Book upDateBook(int bookId, BookEntity bookEntity) {
        bookRepo.findById(bookId).orElseThrow(() -> new BookNotFoundException("Книга с id=" + bookId + " не существует"));
        BookEntity bookUpDate = bookRepo.findById(bookId).get();
        bookUpDate.setTitle(bookEntity.getTitle());
        bookUpDate.setAuthor(bookEntity.getAuthor());
        bookRepo.save(bookUpDate);
        return Book.toModel(bookUpDate);
    }


}
