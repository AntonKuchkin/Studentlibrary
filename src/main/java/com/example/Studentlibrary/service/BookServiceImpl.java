package com.example.Studentlibrary.service;

import com.example.Studentlibrary.dto.Book;
import com.example.Studentlibrary.entity.BookEntity;
import com.example.Studentlibrary.entity.StudentEntity;
import com.example.Studentlibrary.exception.BookException;
import com.example.Studentlibrary.exception.BookNotFoundException;
import com.example.Studentlibrary.repository.BookRepo;
import com.example.Studentlibrary.repository.StudentRepo;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepo bookRepo;
    private final StudentRepo studentRepo;

    public BookServiceImpl(BookRepo bookRepo, StudentRepo studentRepo) {
        this.bookRepo = bookRepo;
        this.studentRepo = studentRepo;
    }

    public BookEntity bookNew(BookEntity book) {

        if (bookRepo.findByTitle(book.getTitle()) != null) {
            throw new BookException("Книга существует");
        } else {
            return bookRepo.save(book);
        }
    }

    public Book getOne(int id) {
        BookEntity book = bookRepo.findById(id).get();
        if (book == null) {
            throw new BookNotFoundException("Книге не существует");
        } else {
            return Book.toModel(book);
        }
    }

    public Integer deleteBook(int id) {
        BookEntity book = bookRepo.findById(id).get();
        if (book == null) {
            throw new BookNotFoundException("Книге не существует");
        } else {
            bookRepo.deleteById(id);
            return id;
        }
    }

    public Book bookAddStudent(BookEntity bookEntity, int studentId) {
        StudentEntity student = studentRepo.findById(studentId).get();
        bookEntity.setStudent(student);
        bookRepo.save(bookEntity);
        return Book.toModel(bookEntity);
    }

    public BookEntity bookByStudent(int bookId) {
        BookEntity bookEntity = bookRepo.findById(bookId).get();
        bookEntity.setBookToLibrarian(!bookEntity.isBookToLibrarian());
        return bookRepo.save(bookEntity);
    }


}
