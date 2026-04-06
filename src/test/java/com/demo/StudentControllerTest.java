package com.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldReturnAllStudents() throws Exception {
        mockMvc.perform(get("/students"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.length()").value(3))
               .andExpect(jsonPath("$[0].name").value("Alice"));
    }

    @Test
    void shouldReturnStudentById() throws Exception {
        mockMvc.perform(get("/students/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("Alice"))
               .andExpect(jsonPath("$.email").value("alice@demo.com"));
    }

    @Test
    void shouldReturn404ForUnknownId() throws Exception {
        mockMvc.perform(get("/students/999"))
               .andExpect(status().isNotFound());  // expects 404, not 500
    }

}
