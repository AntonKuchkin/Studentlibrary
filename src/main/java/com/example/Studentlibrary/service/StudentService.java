package com.example.Studentlibrary.service;

import com.example.Studentlibrary.dto.Student;
import com.example.Studentlibrary.entity.StudentEntity;

public interface StudentService {

    StudentEntity studentNew(StudentEntity student);

    Student getOne(int id);

    Integer deleteStudent(int id);
}
