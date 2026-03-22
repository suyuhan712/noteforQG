package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface StudentRepository extends JpaRepository<Student,Long> {

    List<Student> findByEmail(String email);

}
