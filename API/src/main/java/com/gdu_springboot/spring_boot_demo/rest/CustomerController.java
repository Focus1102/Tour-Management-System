package com.gdu_springboot.spring_boot_demo.rest;

import com.gdu_springboot.spring_boot_demo.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final String CUSTOMER_SERVICE_URL = "http://localhost:8080/api/customers";
    
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public ResponseEntity<CustomerDTO[]> getAllCustomers() {
        return restTemplate.getForEntity(CUSTOMER_SERVICE_URL, CustomerDTO[].class);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable int id) {
        return restTemplate.getForEntity(CUSTOMER_SERVICE_URL + "/" + id, CustomerDTO.class);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customer) {
        return restTemplate.postForEntity(CUSTOMER_SERVICE_URL, customer, CustomerDTO.class);
    }

    @PutMapping("/{id}")
    public void updateCustomer(@PathVariable int id, @RequestBody CustomerDTO customer) {
        restTemplate.put(CUSTOMER_SERVICE_URL + "/" + id, customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable int id) {
        restTemplate.delete(CUSTOMER_SERVICE_URL + "/" + id);
    }
} 