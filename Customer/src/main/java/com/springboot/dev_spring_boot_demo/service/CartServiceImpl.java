package com.springboot.dev_spring_boot_demo.service;

import com.springboot.dev_spring_boot_demo.entity.Cart;
import com.springboot.dev_spring_boot_demo.entity.CartItem;
import com.springboot.dev_spring_boot_demo.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class CartServiceImpl implements CartService {
    
    private final EntityManager entityManager;

    @Autowired
    public CartServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Cart getCartByUserId(String userId) {
        Cart cart = entityManager.createQuery(
                "SELECT c FROM Cart c WHERE c.userId = :userId", Cart.class)
                .setParameter("userId", userId)
                .getResultStream()
                .findFirst()
                .orElse(null);

        if (cart == null) {
            cart = new Cart(userId);
            entityManager.persist(cart);
        }

        return cart;
    }

    @Override
    @Transactional
    public Cart addItemToCart(String userId, Product product, int quantity) {
        Cart cart = getCartByUserId(userId);
        
        // Check if product already exists in cart
        CartItem existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId() == product.getId())
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            CartItem newItem = new CartItem(cart, product, quantity, BigDecimal.valueOf(product.getPrice()));
            cart.addItem(newItem);
        }

        return entityManager.merge(cart);
    }

    @Override
    @Transactional
    public Cart removeItemFromCart(String userId, Long productId) {
        Cart cart = getCartByUserId(userId);
        
        cart.getItems().removeIf(item -> item.getProduct().getId() == productId);
        
        return entityManager.merge(cart);
    }

    @Override
    @Transactional
    public void clearCart(String userId) {
        Cart cart = getCartByUserId(userId);
        cart.clear();
        entityManager.merge(cart);
    }

    @Override
    @Transactional
    public Cart updateItemQuantity(String userId, Long productId, int quantity) {
        Cart cart = getCartByUserId(userId);
        
        cart.getItems().stream()
                .filter(item -> item.getProduct().getId() == productId)
                .findFirst()
                .ifPresent(item -> item.setQuantity(quantity));
        
        return entityManager.merge(cart);
    }
} 