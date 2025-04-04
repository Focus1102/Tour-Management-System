package com.springboot.dev_spring_boot_demo.service;

import com.springboot.dev_spring_boot_demo.entity.Customer;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();

    Customer findById(int id);

    Customer save(Customer customer);

    @Transactional
    void deleteById(int id);
}
