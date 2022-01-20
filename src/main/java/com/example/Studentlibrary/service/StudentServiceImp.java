package com.example.Studentlibrary.service;

import com.example.Studentlibrary.dto.Student;
import com.example.Studentlibrary.entity.StudentEntity;
import com.example.Studentlibrary.exception.StudentNotFoundExeption;
import com.example.Studentlibrary.repository.StudentRepo;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImp implements StudentService {
    private final StudentRepo studentRepo;

    public StudentServiceImp(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public StudentEntity studentNew(StudentEntity student) {
        if (studentRepo.findByNameStudent(student.getNameStudent()) != null) {
            throw new SecurityException("Студент уже зарегистрирован");
        } else {
            return studentRepo.save(student);
        }
    }

    public Student getOne(int id) {
        StudentEntity student = studentRepo.findById(id).get();
        if (student == null) {
            throw new StudentNotFoundExeption("Студент не существует");
        } else {
            return Student.toModel(student);
        }
    }

    public Integer deleteStudent(int id) {
        StudentEntity student = studentRepo.findById(id).get();
        if (student == null) {
            throw new StudentNotFoundExeption("Студент не существует");
        } else {
            studentRepo.deleteById(id);
            return id;
        }
    }

}
