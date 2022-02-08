package com.example.studentlibrary.service;

import com.example.studentlibrary.dto.Student;
import com.example.studentlibrary.entity.StudentEntity;

public interface StudentService {

    Student createStudent(StudentEntity student);

    Student getStudentById(int id);

    Integer deleteStudent(int id);
}
