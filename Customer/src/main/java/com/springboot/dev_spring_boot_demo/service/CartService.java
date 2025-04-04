package com.springboot.dev_spring_boot_demo.service;

import com.springboot.dev_spring_boot_demo.entity.Cart;
import com.springboot.dev_spring_boot_demo.entity.Product;

public interface CartService {
    Cart getCartByUserId(String userId);
    Cart addItemToCart(String userId, Product product, int quantity);
    Cart removeItemFromCart(String userId, Long productId);
    void clearCart(String userId);
    Cart updateItemQuantity(String userId, Long productId, int quantity);
} 