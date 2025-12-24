package com.smartschool.resource;

import com.smartschool.dto.StudentDto;
import com.smartschool.model.Student;
import com.smartschool.resource.dto.StudentRequest;
import com.smartschool.service.StudentService;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/student")
public class StudentResource {

    private final StudentService studentService;

    public StudentResource(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/")
    public ResponseEntity<StudentDto> save(@RequestBody StudentRequest studentRequest) {
        Student user = studentService.save(studentRequest.toEntity());
        return new ResponseEntity<>(user.toDto(), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<StudentDto>> findAll() {
        List<StudentDto> users = studentService.findAll()
            .stream()
            .map(Student::toDto)
            .toList();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> findById(@PathVariable(name = "id") Long id) {
        StudentDto student = studentService.findById(id).toDto();
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> update(@PathVariable Long id,
                                             @Valid @RequestBody StudentRequest studentRequest) {
        Student existingStudent = studentService.findById(id);
        existingStudent.setName(studentRequest.name());
        existingStudent.setLastName(studentRequest.lastName());
        existingStudent.setEmail(studentRequest.email());
        existingStudent.setPhone(studentRequest.phone());
        existingStudent.setDateOfBirth(studentRequest.dateOfBirth());
        Student updatedStudent = studentService.save(existingStudent);
        return ResponseEntity.ok(updatedStudent.toDto());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
