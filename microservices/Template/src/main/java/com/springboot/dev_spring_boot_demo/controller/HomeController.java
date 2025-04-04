package com.springboot.dev_spring_boot_demo.controller;

import com.springboot.dev_spring_boot_demo.entity.Product;
import com.springboot.dev_spring_boot_demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final ProductService productService;

    @Autowired
    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/shop")
    public String shop(@RequestParam(required = false) String category, Model model) {
        List<Product> products;
        if (category != null && !category.isEmpty()) {
            products = productService.findByCategory(category);
        } else {
            products = productService.findAll();
        }
        
        // Lấy danh sách các category duy nhất
        Set<String> categories = products.stream()
                .map(Product::getCategory)
                .collect(Collectors.toSet());
        
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "shop";
    }

    @GetMapping("/about-us")
    public String aboutUs() {
        return "about-us";
    }

    @GetMapping("/service")
    public String service() {
        return "service";
    }

    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }

    @GetMapping("/contact-us")
    public String contactUs() {
        return "contact-us";
    }
}
