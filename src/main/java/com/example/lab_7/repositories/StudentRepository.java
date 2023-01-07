package com.example.lab_7.repositories;

import com.example.lab_7.entities.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Integer> {

    public Student findStudentById(Integer id);

    public List<Student> findByOrderByIdAsc();
}