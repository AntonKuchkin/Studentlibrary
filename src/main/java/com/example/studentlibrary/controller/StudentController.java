package com.example.studentlibrary.controller;

import com.example.studentlibrary.dto.Student;
import com.example.studentlibrary.entity.StudentEntity;
import com.example.studentlibrary.service.StudentServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentServiceImp studentServiceImp;

    @PostMapping("/add")
    public Student createStudent(@RequestBody StudentEntity studentEntity) {
        return studentServiceImp.createStudent(studentEntity);
    }

    @GetMapping("/id")
    public Student getOneStudent(@RequestParam Integer id) {
        return studentServiceImp.getStudentById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Integer id) {
        studentServiceImp.deleteStudent(id);
    }

    @PutMapping("/update")
    public Student upDateStudent(@RequestParam Integer studentId, @RequestBody StudentEntity studentEntity) {
        return studentServiceImp.updateStudent(studentId, studentEntity);
    }
}
