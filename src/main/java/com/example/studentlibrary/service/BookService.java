package com.example.studentlibrary.service;

import com.example.studentlibrary.dto.Book;
import com.example.studentlibrary.entity.BookEntity;

public interface BookService {

    Book createBook(BookEntity bookEntity);

    Book getBookById(int bookId);

    void deleteBook(int bookId);

    Book updateBook(int bookId, BookEntity book);

}
