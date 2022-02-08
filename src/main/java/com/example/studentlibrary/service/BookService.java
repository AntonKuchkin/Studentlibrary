package com.example.studentlibrary.service;

import com.example.studentlibrary.dto.Book;
import com.example.studentlibrary.entity.BookEntity;

public interface BookService {

    Book createBook(BookEntity book);

    Book getBookById(int id);

    Integer deleteBook(int id);

    Book addBookStudent(BookEntity bookEntity, int studentId);

    BookEntity bookByStudent(int BookId);

}
