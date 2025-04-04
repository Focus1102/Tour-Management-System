package com.gdu_springboot.spring_boot_demo.service;

import com.gdu_springboot.spring_boot_demo.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StudentService {
    List<Student> findAll();
    Student findById(int id);
    Student save(Student student);
    void deleteById(int id);
}
