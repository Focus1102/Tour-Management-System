package com.gdu_springboot.spring_boot_demo.dao;

import com.gdu_springboot.spring_boot_demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class StudentDAOImp implements StudentDAO {
    private EntityManager em;

    public StudentDAOImp(EntityManager em) {
        this.em = em;
    }

    @Override
    public Student getStudentById(int id) {
        return em.find(Student.class, id);
    }

    @Override
    public List<Student> getAllStudents() {
        TypedQuery<Student> query = em.createQuery("select s from Student s", Student.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Student addStudent(Student student) {
        return em.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudent(int id) {
        em.remove(em.find(Student.class, id));
    }
}
