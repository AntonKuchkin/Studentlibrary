package com.example.studentlibrary.dto;

import com.example.studentlibrary.entity.StudentEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class Student {
    private int id;
    private String studentName;
    private String faculty;
    private List<Book> books;

    public static Student createToModel(StudentEntity studentEntity) {
        Student modelStudent = new Student();
        modelStudent.setId(studentEntity.getId());
        modelStudent.setStudentName(studentEntity.getNameStudent());
        modelStudent.setFaculty(studentEntity.getFaculty());
        return modelStudent;
    }

    public static Student toModel(StudentEntity studentEntity) {
        Student modelStudent = new Student();
        modelStudent.setStudentName(studentEntity.getNameStudent());
        modelStudent.setFaculty(studentEntity.getFaculty());
        modelStudent.setBooks(studentEntity.getBooks().stream().map(Book::toModel).collect(Collectors.toList()));
        return modelStudent;
    }

}
