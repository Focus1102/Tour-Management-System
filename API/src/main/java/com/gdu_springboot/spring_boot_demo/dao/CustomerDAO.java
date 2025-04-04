package com.gdu_springboot.spring_boot_demo.dao;

import com.gdu_springboot.spring_boot_demo.entity.Customer;

import java.util.List;

public interface CustomerDAO {
    Customer getCustomerById(int id);
    List<Customer> getAllCustomers();
    Customer addCustomer(Customer customer);
    void deleteCustomer(int id);
} 