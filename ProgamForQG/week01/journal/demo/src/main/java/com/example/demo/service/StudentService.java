package com.example.demo.service;

import com.example.demo.dao.Student;
import com.example.demo.dto.StudentDTO;

public interface StudentService {
     StudentDTO getStudentById(Long id);

     Long addNewStudent(StudentDTO studentDTO);

     void deleteStudentById(Long id);

     StudentDTO updateStudentById(Long id, String name, String email);
}


