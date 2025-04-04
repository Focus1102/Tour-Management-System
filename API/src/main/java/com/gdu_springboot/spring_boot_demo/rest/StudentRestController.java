package com.gdu_springboot.spring_boot_demo.rest;

import com.gdu_springboot.spring_boot_demo.dao.StudentDAO;
import com.gdu_springboot.spring_boot_demo.entity.Student;
import com.gdu_springboot.spring_boot_demo.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private StudentService studentService;

    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentService.findAll();
    }
    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable int id) {
        return studentService.findById(id);
    }
    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student) {
        student.setId(0);
        studentService.save(student);
        return student;
    }
    @PutMapping("/students")
    public Student updateStudent(@RequestBody Student student) {
        Student oldStudent = studentService.save(student);
        return oldStudent;
    }
    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentService.deleteById(id);
        return "Delete success has id = "+ id;
    }
}
