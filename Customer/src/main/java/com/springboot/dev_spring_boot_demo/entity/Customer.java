package com.springboot.dev_spring_boot_demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

//    @NotNull(message="Tên công ty không được để trống")
//    @Size(min = 1, message = "Tên công ty không được để trống")
//    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9 ]+$", message = "Tên công ty phải chứa cả chữ và số")
//    @Column(name = "name_company")
//    private String nameCompany;

    @NotNull(message = "Tên công ty không được để trống")
    @Size(min = 8, max = 8, message = "Tên công ty phải có đúng 8 ký tự")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9 ]+$", message = "Tên công ty phải chứa cả chữ và số")
    @Column(name = "name_company")
    private String nameCompany;

    @NotNull(message = "Địa chỉ không được để trống")
    @Size(min = 1, message = "Địa chỉ không được để trống")
    @Column(name = "street_address")
    private String streetAddress;

    @NotNull(message = "Thành phố không được để trống")
    @Size(min = 1, message = "Thành phố không được để trống")
    @Column(name = "city")
    private String city;

    @NotNull(message = "Quốc gia không được để trống")
    @Size(min = 1, message = "Quốc gia không được để trống")
    @Column(name = "country")
    private String country;

    @NotNull(message = "Khu vực không được để trống")
    @Size(min = 1, message = "Khu vực không được để trống")
    @Column(name = "region")
    private String region;

    @NotNull(message = "Mã bưu chính không được để trống")
    @Size(min = 5, max = 10, message = "Mã bưu chính phải có độ dài từ 5 đến 10 ký tự")
    @Pattern(regexp = "^[0-9]*$", message = "Mã bưu chính chỉ được chứa số")
    @Column(name = "post_code")
    private String postCode;

    // Constructor mặc định
    public Customer() {
    }


    // Getter và Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}


