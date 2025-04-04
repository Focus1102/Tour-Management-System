package com.gdu_springboot.spring_boot_demo.service;

import com.gdu_springboot.spring_boot_demo.dao.CustomerDAO;
import com.gdu_springboot.spring_boot_demo.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService {
    private CustomerDAO customerDAO;

    public CustomerServiceImp(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public List<Customer> findAll() {
        return customerDAO.getAllCustomers();
    }

    @Override
    public Customer findById(int id) {
        return customerDAO.getCustomerById(id);
    }

    @Override
    public Customer save(Customer customer) {
        customerDAO.addCustomer(customer);
        return customer;
    }

    @Override
    public void deleteById(int id) {
        customerDAO.deleteCustomer(id);
    }
} 