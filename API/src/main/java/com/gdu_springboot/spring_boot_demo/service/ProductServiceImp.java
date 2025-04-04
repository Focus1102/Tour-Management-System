package com.gdu_springboot.spring_boot_demo.service;

import com.gdu_springboot.spring_boot_demo.dao.ProductDAO;
import com.gdu_springboot.spring_boot_demo.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
    private ProductDAO productDAO;

    public ProductServiceImp(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public List<Product> findAll() {
        return productDAO.getAllProducts();
    }

    @Override
    public Product findById(int id) {
        return productDAO.getProductById(id);
    }

    @Override
    public Product save(Product product) {
        productDAO.addProduct(product);
        return product;
    }

    @Override
    public void deleteById(int id) {
        productDAO.deleteProduct(id);
    }
} 