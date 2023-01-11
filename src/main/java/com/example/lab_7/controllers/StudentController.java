package com.example.lab_7.controllers;

import com.example.lab_7.entities.Student;
import com.example.lab_7.services.StudentService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<JSONObject> addStudent(@RequestBody Student student) {
        if (!studentService.addStudent(student)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("statusCode", HttpStatus.BAD_REQUEST.value());
            jsonObject.put("message", "Wystąpił problem z dodawaniem nowego studenta.");
            return new ResponseEntity<JSONObject>(jsonObject, HttpStatus.BAD_REQUEST);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("statusCode", HttpStatus.CREATED.value());
        jsonObject.put("message", "Pomyślnie dodano nowego studenta.");
        return new ResponseEntity<JSONObject>(jsonObject, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<JSONObject> deleteStudent(@PathVariable String id) {
        if (studentService.isInteger(id)) {
            Integer idStudent = Integer.parseInt(id);
            if (!studentService.deleteStudent(idStudent)) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("statusCode", HttpStatus.NOT_FOUND.value());
                jsonObject.put("message", "Nie znaleziono studenta o id: " + idStudent);
                return new ResponseEntity<JSONObject>(jsonObject, HttpStatus.NOT_FOUND);
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("statusCode", HttpStatus.OK.value());
            jsonObject.put("message", "Pomyślnie usunięto studenta o id: " + idStudent);
            return new ResponseEntity<JSONObject>(jsonObject, HttpStatus.OK);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("statusCode", HttpStatus.BAD_REQUEST.value());
        jsonObject.put("message", "Podano nieprawidłowe parametry: Id nie jest typu Integer.");
        return new ResponseEntity<JSONObject>(jsonObject, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/editStudent/{id}")
    public ResponseEntity<JSONObject> editStudent(@RequestBody Student student, @PathVariable String id) {
        if (studentService.isInteger(id)) {
            Integer idStudent = Integer.parseInt(id);
            if (!studentService.updateStudent(student, idStudent)) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("statusCode", HttpStatus.NOT_FOUND.value());
                jsonObject.put("message", "Nie znaleziono studenta o id: " + idStudent);
                return new ResponseEntity<JSONObject>(jsonObject, HttpStatus.NOT_FOUND);
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("statusCode", HttpStatus.OK.value());
            jsonObject.put("message", "Pomyślnie zaktualizowano dane studenta o id: " + idStudent);
            return new ResponseEntity<JSONObject>(jsonObject, HttpStatus.OK);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("statusCode", HttpStatus.BAD_REQUEST.value());
        jsonObject.put("message", "Podano nieprawidłowe parametry: Id nie jest typu Integer.");
        return new ResponseEntity<JSONObject>(jsonObject, HttpStatus.BAD_REQUEST);
    }

}
