package com.gdu_springboot.spring_boot_demo.dao;

import com.gdu_springboot.spring_boot_demo.dao.UserDAO;
import com.gdu_springboot.spring_boot_demo.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Repository
@Transactional
public class UserDAOImp implements UserDAO {

    private final EntityManager entityManager;

    @Autowired
    public UserDAOImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        TypedQuery<User> query = entityManager.createQuery("FROM User", User.class);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username) {
        try {
            TypedQuery<User> query = entityManager.createQuery(
                    "FROM User WHERE username = :username", User.class);
            query.setParameter("username", username);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public User save(User user) {
        if (!existsByUsername(user.getUsername())) {
            entityManager.persist(user);
            return user;
        } else {
            return entityManager.merge(user);
        }
    }

    @Override
    @Transactional
    public void deleteByUsername(String username) {
        // Sử dụng entityManager để xóa trực tiếp cả hai bảng

        // 1. Xóa authorities trước
        Query deleteAuthQuery = entityManager.createNativeQuery(
                "DELETE FROM authorities WHERE username = ?");
        deleteAuthQuery.setParameter(1, username);
        deleteAuthQuery.executeUpdate();

        // 2. Sau đó xóa user
        Query deleteUserQuery = entityManager.createNativeQuery(
                "DELETE FROM users WHERE username = ?");
        deleteUserQuery.setParameter(1, username);
        int deleted = deleteUserQuery.executeUpdate();

        if (deleted == 0) {
            throw new RuntimeException("Không tìm thấy user với username: " + username);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByUsername(String username) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(u) FROM User u WHERE u.username = :username", Long.class);
        query.setParameter("username", username);
        return query.getSingleResult() > 0;
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(u) FROM User u", Long.class);
        return query.getSingleResult();
    }
}