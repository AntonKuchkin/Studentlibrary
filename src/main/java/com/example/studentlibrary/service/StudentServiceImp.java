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

    public Student createStudent(StudentEntity studentEntity) {
        if (studentRepo.existsStudentEntitiesByNameStudent(studentEntity.getNameStudent())) {
            throw new SecurityException("Студент уже зарегистрирован");
        } else {
            studentRepo.save(studentEntity);
            return Student.createToModel(studentEntity);
        }
    }

    public Student getStudentById(int studentId) {
        studentRepo.findById(studentId).orElseThrow(() -> new StudentNotFoundExeption("Студента с id=" + studentId + " не существует"));
        StudentEntity student = studentRepo.findById(studentId).get();
        return Student.toModel(student);
    }

    public void deleteStudent(int studentId) {
        studentRepo.findById(studentId).orElseThrow(() -> new StudentNotFoundExeption("Студента с id=" + studentId + " не существует"));
        studentRepo.deleteById(studentId);
    }

    @Override
    public Student upDateStudent(int studentId, StudentEntity studentEntity) {
        studentRepo.findById(studentId).orElseThrow(() -> new StudentNotFoundExeption("Студента с id=" + studentId + " не существует"));
        StudentEntity studentUpDate = studentRepo.findById(studentId).get();
        studentUpDate.setNameStudent(studentEntity.getNameStudent());
        studentUpDate.setFaculty(studentEntity.getFaculty());
        studentRepo.save(studentUpDate);
        return Student.createToModel(studentUpDate);
    }

}
