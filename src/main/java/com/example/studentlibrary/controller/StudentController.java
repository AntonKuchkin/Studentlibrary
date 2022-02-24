package com.example.studentlibrary.controller;

import com.example.studentlibrary.dto.Student;
import com.example.studentlibrary.entity.StudentEntity;
import com.example.studentlibrary.service.StudentServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentServiceImp studentServiceImp;

    @PostMapping("/add")
    public Student createStudent(@RequestBody StudentEntity studentEntity) {
        Student student = studentServiceImp.createStudent(studentEntity);
        return student;
    }

    @GetMapping("/id")
    public Student getOneStudent(@RequestParam Integer id) {
        Student student = studentServiceImp.getStudentById(id);
        return student;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable Integer id) {
        studentServiceImp.deleteStudent(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    public Student upDateStudent(@RequestParam Integer studentId, @RequestBody StudentEntity studentEntity) {
        return studentServiceImp.upDateStudent(studentId, studentEntity);
    }
}
