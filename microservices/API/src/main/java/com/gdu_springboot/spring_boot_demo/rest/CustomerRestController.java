package com.gdu_springboot.spring_boot_demo.rest;

import com.gdu_springboot.spring_boot_demo.entity.Customer;
import com.gdu_springboot.spring_boot_demo.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
    private CustomerService customerService;

    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerService.findAll();
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomer(@PathVariable int id) {
        return customerService.findById(id);
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer) {
        customer.setId(0);
        customerService.save(customer);
        return customer;
    }

    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer customer) {
        Customer updatedCustomer = customerService.save(customer);
        return updatedCustomer;
    }

    @DeleteMapping("/customers/{id}")
    public String deleteCustomer(@PathVariable int id) {
        customerService.deleteById(id);
        return "Delete success has id = " + id;
    }
} 