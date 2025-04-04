package com.gdu_springboot.spring_boot_demo.dao;

import com.gdu_springboot.spring_boot_demo.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImp implements CustomerDAO {
    private EntityManager em;

    public CustomerDAOImp(EntityManager em) {
        this.em = em;
    }

    @Override
    public Customer getCustomerById(int id) {
        return em.find(Customer.class, id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        TypedQuery<Customer> query = em.createQuery("select c from Customer c", Customer.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Customer addCustomer(Customer customer) {
        return em.merge(customer);
    }

    @Override
    @Transactional
    public void deleteCustomer(int id) {
        em.remove(em.find(Customer.class, id));
    }
} 