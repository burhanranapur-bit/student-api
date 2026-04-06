package com.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final List<Student> students = List.of(
        new Student(1L, "Alice", "alice@demo.com"),
        new Student(2L, "Bob",   "bob@demo.com"),
        new Student(3L, "Carol", "carol@demo.com")
    );

    @GetMapping
    public List<Student> getAll() {
        return students;
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) {
        Optional<Student> found = students.stream()
            .filter(s -> s.getId().equals(id))
            .findFirst();

        if (found.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Student not found: " + id
            );
        }

        return found.get();
    }
}
