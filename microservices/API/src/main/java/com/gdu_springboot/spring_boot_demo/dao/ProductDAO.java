package com.gdu_springboot.spring_boot_demo.dao;

import com.gdu_springboot.spring_boot_demo.entity.Product;

import java.util.List;

public interface ProductDAO {
    Product getProductById(int id);
    List<Product> getAllProducts();
    Product addProduct(Product product);
    void deleteProduct(int id);
} 