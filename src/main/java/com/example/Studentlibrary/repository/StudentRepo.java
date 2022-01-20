package com.example.Studentlibrary.repository;

import com.example.Studentlibrary.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepo extends CrudRepository<StudentEntity, Integer> {
    StudentEntity findByNameStudent(String name);
}
