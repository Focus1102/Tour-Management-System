package com.springboot.dev_spring_boot_demo.controller;

import com.springboot.dev_spring_boot_demo.entity.Customer;
import com.springboot.dev_spring_boot_demo.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/list-customer")
    public String list(Model model) {
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "admin/customers/list-customer";
    }

    @GetMapping("/customer-form-insert")
    public String customerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "admin/customers/customer-form-insert";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult) {
        System.out.println("BindingResult has errors: " + bindingResult.hasErrors()); // Debug
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage())); // Debug
            return "admin/customers/customer-form-insert";
        }
        customerService.save(customer);
        return "redirect:/admin/customers/list-customer";
    }

    @GetMapping("/customer-form-update")
    public String customerFormUpdate(@RequestParam("ids") int id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer_a", customer);
        return "admin/customers/customer-form-update";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("ids") int id) {
        customerService.deleteById(id);
        return "redirect:/admin/customers/list-customer";
    }
}
