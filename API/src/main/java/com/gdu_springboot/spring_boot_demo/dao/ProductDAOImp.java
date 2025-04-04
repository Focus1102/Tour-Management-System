package com.gdu_springboot.spring_boot_demo.dao;

import com.gdu_springboot.spring_boot_demo.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImp implements ProductDAO {
    private EntityManager em;

    public ProductDAOImp(EntityManager em) {
        this.em = em;
    }

    @Override
    public Product getProductById(int id) {
        return em.find(Product.class, id);
    }

    @Override
    public List<Product> getAllProducts() {
        TypedQuery<Product> query = em.createQuery("select p from Product p", Product.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Product addProduct(Product product) {
        return em.merge(product);
    }

    @Override
    @Transactional
    public void deleteProduct(int id) {
        em.remove(em.find(Product.class, id));
    }
} 