package com.gdu_springboot.spring_boot_demo.dao;

import com.gdu_springboot.spring_boot_demo.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    List<User> findAll();

    Optional<User> findByUsername(String username);

    User save(User user);

    void deleteByUsername(String username);

    boolean existsByUsername(String username);

    long count();
}