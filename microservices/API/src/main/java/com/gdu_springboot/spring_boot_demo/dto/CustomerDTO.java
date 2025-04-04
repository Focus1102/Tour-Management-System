package com.gdu_springboot.spring_boot_demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDTO {
    private int id;

    @NotNull(message = "Tên công ty không được để trống")
    @Size(min = 8, max = 8, message = "Tên công ty phải có đúng 8 ký tự")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9 ]+$", message = "Tên công ty phải chứa cả chữ và số")
    private String nameCompany;

    @NotNull(message = "Địa chỉ không được để trống")
    @Size(min = 1, message = "Địa chỉ không được để trống")
    private String streetAddress;

    @NotNull(message = "Thành phố không được để trống")
    @Size(min = 1, message = "Thành phố không được để trống")
    private String city;

    @NotNull(message = "Quốc gia không được để trống")
    @Size(min = 1, message = "Quốc gia không được để trống")
    private String country;

    @NotNull(message = "Khu vực không được để trống")
    @Size(min = 1, message = "Khu vực không được để trống")
    private String region;

    @NotNull(message = "Mã bưu chính không được để trống")
    @Size(min = 5, max = 10, message = "Mã bưu chính phải có độ dài từ 5 đến 10 ký tự")
    @Pattern(regexp = "^[0-9]*$", message = "Mã bưu chính chỉ được chứa số")
    private String postCode;
} 