package com.springboot.dev_spring_boot_demo.dao;

import com.springboot.dev_spring_boot_demo.entity.Customer;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CustomerDAO {
    List<Customer> findAll();

    Customer findById(int id);

    @Transactional
    Customer save(Customer customer);

    @Transactional
    void deleteById(int id);
}
