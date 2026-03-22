package com.example.demo.dao;

import jakarta.persistence.*;

@Entity
@Table(name="student")

public class Student {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="age")
    private int age;

    public long getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String setEmail(String email) {
        return email;
    }

    public String setName(String name) {
        return name;
    }
}
