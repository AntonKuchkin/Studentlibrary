package com.example.studentlibrary.service;

import com.example.studentlibrary.dto.Student;
import com.example.studentlibrary.entity.StudentEntity;

public interface StudentService {

    Student createStudent(StudentEntity studentEntity);

    Student getStudentById(int studentId);

    void deleteStudent(int studentId);

    Student upDateStudent(int studentId, StudentEntity studentEntity);
}
