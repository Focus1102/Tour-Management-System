package com.gdu_springboot.spring_boot_demo.rest;

import com.gdu_springboot.spring_boot_demo.entity.Product;
import com.gdu_springboot.spring_boot_demo.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductRestController {
    private ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.findAll();
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable int id) {
        return productService.findById(id);
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        product.setId(0);
        productService.save(product);
        return product;
    }

    @PutMapping("/products")
    public Product updateProduct(@RequestBody Product product) {
        Product updatedProduct = productService.save(product);
        return updatedProduct;
    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.deleteById(id);
        return "Delete success has id = " + id;
    }
} 