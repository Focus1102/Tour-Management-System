package com.springboot.dev_spring_boot_demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message = "Tên không được để trống")
    @Size(min = 1, message = "Tên không được để trống")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Tên chỉ được chứa chữ cái và khoảng trắng")
    @Column(name = "first_name")
    private String first_name;

    @NotNull(message = "Họ không được để trống")
    @Size(min = 1, message = "Họ không được để trống")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Họ chỉ được chứa chữ cái và khoảng trắng")
    @Column(name = "last_name")
    private String last_name;

    @NotNull(message = "Email không được để trống")
    @Size(min = 5, max = 50, message = "Email phải có độ dài từ 5 đến 50 ký tự")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email không đúng định dạng")
    @Column(name = "email")
    private String email;

    public Student() {
    }

    public Student(String first_name, String last_name, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}