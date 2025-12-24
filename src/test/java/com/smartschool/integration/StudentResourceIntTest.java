package com.smartschool.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.smartschool.containers.IntegrationTest;
import com.smartschool.resource.dto.StudentRequest;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@IntegrationTest
class StudentResourceIntTest {

    private final String BASE_URL = "/api/student/";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateStudent() throws Exception {
        StudentRequest studentRequest = new StudentRequest("Andre", "Alves",
            "andre.alves@alves.com", "1234567890", LocalDate.of(1980,11,9),
            "2024001");
        String studentRequestJson = new ObjectMapper()
            .registerModule(new JavaTimeModule()).writeValueAsString(studentRequest);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .post(BASE_URL)
            .contentType("application/json")
            .accept("application/json")
            .content(studentRequestJson);

        mockMvc
         .perform(request)
         .andExpect(MockMvcResultMatchers.status().isCreated());
    }



}
