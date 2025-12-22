package com.smartschool.service;

import com.smartschool.model.Student;

import java.util.List;

public interface StudentService {
    Student save(Student student);

    List<Student> findAll();

    Student findById(Long id);

    void deleteById(Long id);
}
