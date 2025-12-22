package com.smartschool.service.impl;

import com.smartschool.expection.NotFoundException;
import com.smartschool.model.Student;
import com.smartschool.repository.StudentRepository;
import com.smartschool.service.StudentService;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student save(Student user) {
       return studentRepository.save(user);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id)
            .orElseThrow( () -> new NotFoundException("Student not found with id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

}
