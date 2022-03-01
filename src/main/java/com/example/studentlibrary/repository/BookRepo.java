package com.example.studentlibrary.repository;

import com.example.studentlibrary.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;

public interface BookRepo extends CrudRepository<BookEntity, Integer> {
    boolean existsBookEntitiesByTitle(String title);
}