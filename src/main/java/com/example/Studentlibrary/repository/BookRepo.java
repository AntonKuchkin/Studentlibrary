package com.example.Studentlibrary.repository;

import com.example.Studentlibrary.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;

public interface BookRepo extends CrudRepository<BookEntity, Integer> {
    BookEntity findByTitle(String title);
}