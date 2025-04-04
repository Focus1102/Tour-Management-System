package com.springboot.dev_spring_boot_demo.controller;

import com.springboot.dev_spring_boot_demo.entity.Cart;
import com.springboot.dev_spring_boot_demo.entity.Product;
import com.springboot.dev_spring_boot_demo.service.CartService;
import com.springboot.dev_spring_boot_demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CartController {
    
    private final CartService cartService;
    private final ProductService productService;

    @Autowired
    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }
    
    @GetMapping("/cart")
    public String viewCart(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            Cart cart = cartService.getCartByUserId(auth.getName());
            model.addAttribute("cart", cart);
        }
        return "cart";
    }

    @PostMapping("/api/cart/add")
    @ResponseBody
    public ResponseEntity<?> addToCart(@RequestParam("productId") Long productId,
                                     @RequestParam(value = "quantity", defaultValue = "1") int quantity) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return ResponseEntity.status(401).body("User not authenticated");
        }

        Product product = productService.findById(productId);
        if (product == null) {
            return ResponseEntity.badRequest().body("Product not found");
        }

        Cart cart = cartService.addItemToCart(auth.getName(), product, quantity);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/api/cart/remove/{productId}")
    @ResponseBody
    public ResponseEntity<?> removeFromCart(@PathVariable Long productId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return ResponseEntity.status(401).body("User not authenticated");
        }

        Cart cart = cartService.removeItemFromCart(auth.getName(), productId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/api/cart/update/{productId}")
    @ResponseBody
    public ResponseEntity<?> updateQuantity(@PathVariable Long productId,
                                          @RequestParam("quantity") int quantity) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return ResponseEntity.status(401).body("User not authenticated");
        }

        Cart cart = cartService.updateItemQuantity(auth.getName(), productId, quantity);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/api/cart/clear")
    @ResponseBody
    public ResponseEntity<?> clearCart() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return ResponseEntity.status(401).body("User not authenticated");
        }

        cartService.clearCart(auth.getName());
        return ResponseEntity.ok().build();
    }
} 