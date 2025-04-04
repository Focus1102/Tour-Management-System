package com.springboot.dev_spring_boot_demo.controller;

import com.springboot.dev_spring_boot_demo.entity.Product;
import com.springboot.dev_spring_boot_demo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/products")
public class ProductController {

    private ProductService productService;
    private static final String UPLOAD_DIR = "src/main/resources/static/default/images/";

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/list-product")
    public String listProducts(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "admin/products/list-product";
    }

    @GetMapping("/product-form-insert")
    public String showFormForAdd(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "admin/products/product-form-insert";
    }

    @GetMapping("/product-form-update")
    public String showFormForUpdate(@RequestParam("id") Long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "admin/products/product-form-update";
    }

    @PostMapping("/save")
    public String saveProduct(@Valid @ModelAttribute("product") Product product,
                            BindingResult bindingResult,
                            @RequestParam("image") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            return product.getId() == null ?
                   "admin/products/product-form-insert" :
                   "admin/products/product-form-update";
        }

        if (!file.isEmpty()) {
            try {
                String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                Path path = Paths.get(UPLOAD_DIR + fileName);
                Files.createDirectories(path.getParent());
                Files.write(path, file.getBytes());
                product.setImageUrl(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        productService.save(product);
        return "redirect:/admin/products/list-product";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        productService.deleteById(id);
        return "redirect:/admin/products/list-product";
    }

}