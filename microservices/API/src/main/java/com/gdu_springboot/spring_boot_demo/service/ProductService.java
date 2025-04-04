package com.gdu_springboot.spring_boot_demo.service;

import com.gdu_springboot.spring_boot_demo.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(int id);
    Product save(Product product);
    void deleteById(int id);
} 