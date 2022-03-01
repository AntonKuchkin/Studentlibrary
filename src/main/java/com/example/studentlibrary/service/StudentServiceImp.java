package com.example.studentlibrary.service;

import com.example.studentlibrary.dto.Student;
import com.example.studentlibrary.entity.BookEntity;
import com.example.studentlibrary.entity.StudentEntity;
import com.example.studentlibrary.exception.BookNotFoundException;
import com.example.studentlibrary.exception.StudentNotFoundExeption;
import com.example.studentlibrary.repository.BookRepo;
import com.example.studentlibrary.repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class StudentServiceImp implements StudentService {
    private final StudentRepo studentRepo;
    private final BookRepo bookRepo;

    @Override
    public Student createStudent(StudentEntity studentEntity) {
        if (studentRepo.existsStudentEntitiesByNameStudent(studentEntity.getNameStudent())) {
            throw new SecurityException("Студент с таким именем = " + studentEntity.getNameStudent() + " уже зарегистрирован");
        } else {
            studentRepo.save(studentEntity);
            return Student.createToModel(studentEntity);
        }
    }

    @Override
    public Student getStudentById(int studentId) {
        StudentEntity student = studentRepo.findById(studentId).orElseThrow(() -> new StudentNotFoundExeption("Студента с id=" + studentId + " не существует."));
        return Student.toModel(student);
    }

    @Override
    public void deleteStudent(int studentId) {
        if (studentRepo.existsById(studentId)) {
            studentRepo.deleteById(studentId);
        } else {
            throw new StudentNotFoundExeption("Студента с id=" + studentId + " не существует.");
        }

    }

    @Override
    @Transactional//почитать//если добавить аннотацию то при петвом запросе через постман, не возвращает список книг.
    public Student addBookStudent(int bookId, int studentId) {
        BookEntity bookEntity = bookRepo.findById(bookId).orElseThrow(() -> new BookNotFoundException("Книга с id=" + bookId + " не существует"));
        StudentEntity studentEntity = studentRepo.findById(studentId).orElseThrow(() -> new StudentNotFoundExeption("Студента с id=" + studentId + " не существует"));
        bookEntity.setStudent(studentEntity);
        bookRepo.save(bookEntity);
        return Student.toModel(studentEntity);
    }

    @Override
    public Student updateStudent(int studentId, StudentEntity studentEntity) {
        StudentEntity studentUpDate = studentRepo.findById(studentId).orElseThrow(() -> new StudentNotFoundExeption("Студента с id=" + studentId + " не существует."));
        studentUpDate.setNameStudent(studentEntity.getNameStudent());
        studentUpDate.setFaculty(studentEntity.getFaculty());
        studentRepo.save(studentUpDate);
        return Student.createToModel(studentUpDate);
    }

}
