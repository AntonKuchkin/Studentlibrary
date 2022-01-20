package com.example.Studentlibrary.service;

import com.example.Studentlibrary.dto.Book;
import com.example.Studentlibrary.entity.BookEntity;

public interface BookService {

    BookEntity bookNew(BookEntity book);

    Book getOne(int id);

    Integer deleteBook(int id);

    Book bookAddStudent(BookEntity bookEntity, int studentId);

    BookEntity bookByStudent(int BookId);

}
