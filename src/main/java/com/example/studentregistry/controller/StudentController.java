package com.example.studentregistry.controller;

import com.example.studentregistry.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<Map<Integer, String>> getStudents() {
        Map<Integer, String> students = studentService.getStudents();
        return ResponseEntity.ok(students);
    }

    @PostMapping("/clear-cache")
    public ResponseEntity<String> clearCache() {
        studentService.clearCache();
        return ResponseEntity.ok("Cache cleared successfully. Next GET will reload from source.");
    }
}