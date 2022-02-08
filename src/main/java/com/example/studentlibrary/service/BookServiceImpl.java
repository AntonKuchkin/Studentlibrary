package com.example.studentlibrary.service;

import com.example.studentlibrary.dto.Book;
import com.example.studentlibrary.entity.BookEntity;
import com.example.studentlibrary.entity.StudentEntity;
import com.example.studentlibrary.exception.BookException;
import com.example.studentlibrary.exception.BookNotFoundException;
import com.example.studentlibrary.repository.BookRepo;
import com.example.studentlibrary.repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepo bookRepo;
    private final StudentRepo studentRepo;

    public Book createBook(BookEntity book) {

        if (bookRepo.existsBookEntitiesByTitle(book.getTitle())) {
            throw new BookException("Книга существует");
        } else {
            bookRepo.save(book);
            return Book.toModel(book);
        }
    }

    public Book getBookById(int id) {
        bookRepo.findById(id).orElseThrow(() -> new BookNotFoundException("Книга не существует"));
        BookEntity book = bookRepo.findById(id).get();
        return Book.toModel(book);
    }

    public Integer deleteBook(int id) {
        bookRepo.findById(id).orElseThrow(() -> new BookNotFoundException("Книга не существует"));
        bookRepo.deleteById(id);
        return id;
    }

    public Book addBookStudent(BookEntity bookEntity, int studentId) {
        StudentEntity student = studentRepo.findById(studentId).get();
        bookEntity.setStudent(student);
        bookRepo.save(bookEntity);
        return Book.toModel(bookEntity);
    }

    public BookEntity bookByStudent(int bookId) { // Вообще не понятная логика. Что делает метод ставит флаг, что книгу вернули в библиотеку?// да, но я не cмог реализовать его до конца нормально.
        BookEntity bookEntity = bookRepo.findById(bookId).get();
        bookEntity.setBookToLibrarian(!bookEntity.isBookToLibrarian());
        return bookRepo.save(bookEntity);
    }


}
