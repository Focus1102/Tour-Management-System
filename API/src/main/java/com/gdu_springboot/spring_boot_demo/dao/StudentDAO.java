package com.gdu_springboot.spring_boot_demo.dao;

import com.gdu_springboot.spring_boot_demo.entity.Student;

import java.util.List;

public interface StudentDAO {
    Student getStudentById(int id);
    List<Student> getAllStudents();
    Student addStudent(Student student);
    void deleteStudent(int id);
}
