package com.example.lab_7.controllers;

import com.example.lab_7.entities.Student;
import com.example.lab_7.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public List<Student> getAll() {
        return studentService.getStudentList();
    }

    @PostMapping("/addStudent")
    public void addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public void deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
    }

    @PutMapping("/editStudent/{id}")
    public void editStudent(@RequestBody Student student, @PathVariable String id) {
        studentService.updateStudent(student, id);
    }

}
