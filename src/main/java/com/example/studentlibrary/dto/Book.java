package com.example.studentlibrary.dto;

import com.example.studentlibrary.entity.BookEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Book {

    private String title;
    private String authorName;

    public static Book toModel(BookEntity bookEntity) {
        Book modelBook = new Book();
        modelBook.setTitle(bookEntity.getTitle());
        modelBook.setAuthorName(bookEntity.getAuthor());
        return modelBook;
    }

}
