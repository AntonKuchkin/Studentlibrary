package com.example.studentlibrary.repository;

import com.example.studentlibrary.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepo extends CrudRepository<StudentEntity, Integer> {
    StudentEntity findByNameStudent(String name);
    boolean existsStudentEntitiesByNameStudent (String name);
}
