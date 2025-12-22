package com.smartschool.dto;

import java.time.LocalDate;

public record StudentDto(String name, String lastName, String email,
                         String phone, LocalDate dateOfBirth) {
}
