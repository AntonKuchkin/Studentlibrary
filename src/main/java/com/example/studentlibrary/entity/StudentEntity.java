package com.example.studentlibrary.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int id;
    @Column(name = "name")
    private String nameStudent;
    @Column(name = "faculty")
    private String faculty;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    @JsonManagedReference
    private List<BookEntity> books;

    public StudentEntity() {
    }

    public StudentEntity(int id, String nameStudent, String faculty, List<BookEntity> books) {
        this.id = id;
        this.nameStudent = nameStudent;
        this.faculty = faculty;
        this.books = books;
    }
}
