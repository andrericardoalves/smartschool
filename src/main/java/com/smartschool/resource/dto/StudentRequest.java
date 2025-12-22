package com.smartschool.resource.dto;

import com.smartschool.model.Student;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StudentRequest(@NotBlank String name,
                             @NotBlank String lastName,
                             @NotBlank @Email String email,
                             @NotBlank String phone,
                             @NotNull LocalDate dateOfBirth) {

    public Student toEntity() {
        return Student.builder()
                      .name(this.name)
                      .lastName(this.lastName)
                      .email(this.email)
                      .phone(this.phone)
                      .dateOfBirth(this.dateOfBirth)
                      .build();
    }
}
