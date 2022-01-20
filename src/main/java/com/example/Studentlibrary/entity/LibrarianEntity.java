package com.example.Studentlibrary.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "librarian")
public class LibrarianEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "librarian_id")
    private int id;
    @Column(name = "name")
    private String nameLibrarian;
    @Column(name = "login")
    private String loginLibrarian;

    public LibrarianEntity() {
    }

    public LibrarianEntity(int id, String nameLibrarian, String loginLibrarian) {
        this.id = id;
        this.nameLibrarian = nameLibrarian;
        this.loginLibrarian = loginLibrarian;
    }

}
