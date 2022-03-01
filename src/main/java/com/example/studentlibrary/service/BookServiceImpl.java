package com.example.studentlibrary.service;

import com.example.studentlibrary.dto.Book;
import com.example.studentlibrary.entity.BookEntity;
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

    @Override
    public Book createBook(BookEntity bookEntity) {

        if (bookRepo.existsBookEntitiesByTitle(bookEntity.getTitle())) {
            throw new BookNotFoundException("Книга с таким названием = " + bookEntity.getTitle() + " уже существует");
        } else {
            bookRepo.save(bookEntity);
            return Book.toModel(bookEntity);
        }
    }

    @Override
    public Book getBookById(int bookId) {
        BookEntity book = bookRepo.findById(bookId).orElseThrow(() -> new BookNotFoundException("Книга с id=" + bookId + " не существует"));
        return Book.toModel(book);
    }

    @Override
    public void deleteBook(int bookId) {
        if (bookRepo.existsById(bookId)) {
            bookRepo.deleteById(bookId);
        } else {
            throw new BookNotFoundException("Книга с id=" + bookId + " не существует");
        }

    }

    @Override
    public Book updateBook(int bookId, BookEntity bookEntity) {
        BookEntity bookUpdate = bookRepo.findById(bookId).orElseThrow(() -> new BookNotFoundException("Книга с id=" + bookId + " не существует"));
        bookUpdate.setTitle(bookEntity.getTitle());
        bookUpdate.setAuthor(bookEntity.getAuthor());
        bookRepo.save(bookUpdate);
        return Book.toModel(bookUpdate);
    }


}
