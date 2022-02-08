package com.example.studentlibrary.service;

import com.example.studentlibrary.dto.Student;
import com.example.studentlibrary.entity.StudentEntity;
import com.example.studentlibrary.exception.StudentNotFoundExeption;
import com.example.studentlibrary.repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StudentServiceImp implements StudentService {
    private final StudentRepo studentRepo;

    public Student createStudent(StudentEntity student) {
        if (studentRepo.existsStudentEntitiesByNameStudent(student.getNameStudent())) {
            throw new SecurityException("Студент уже зарегистрирован");
        } else {
            return Student.toModel(student);
        }
    }

    public Student getStudentById(int id) {
        studentRepo.findById(id).orElseThrow(() -> new StudentNotFoundExeption("Студент не существует"));
        StudentEntity student = studentRepo.findById(id).get();
        return Student.toModel(student);
    }

    public Integer deleteStudent(int id) {
        studentRepo.findById(id).orElseThrow(() -> new StudentNotFoundExeption("Студент не существует"));
        studentRepo.deleteById(id);
        return id;
    }

}
