package com.example.lab_7.services;

import com.example.lab_7.entities.Student;
import com.example.lab_7.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getStudentList() {
        return (List<Student>) studentRepository.findByOrderByIdAsc();
    }

    public boolean addStudent(Student student) {
        studentRepository.save(student);
        return true;
    }

    public boolean isInteger(String id) {
        if (!id.isEmpty()) {
            try {
                Integer i = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                // parsowanie się nie powiodło
                return false;
            }
            // parsowanie się powiodło
            return true;
        }
        // podany String jest pusty ("")
        return false;
    }

    public boolean deleteStudent(Integer id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updateStudent(Student student, Integer id) {
        if (studentRepository.existsById(id)) {
            student.setId(id);
            studentRepository.save(student);
            return true;
        }
        return false;
    }

    public Student getStudentById(String id) {
        if (isInteger(id)) {
            Integer idStudent = Integer.parseInt(id);
            if (studentRepository.existsById(idStudent)) {
                return studentRepository.findStudentById(idStudent);
            }
        }
        return null;
    }

    public boolean isEmpty(String value) {
        return !value.isEmpty();
    }

    public boolean isDouble(String value) {
        try {
            Double val = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            // parsowanie się nie powiodło
            return false;
        }
        // parsowanie się powiodło
        return true;
    }

    public boolean checkAverageRange(Double average) {
        return average >= 2.0 && average <= 5.0;
    }

}