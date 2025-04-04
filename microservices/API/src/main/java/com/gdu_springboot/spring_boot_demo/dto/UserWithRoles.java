package com.gdu_springboot.spring_boot_demo.dto;

import com.gdu_springboot.spring_boot_demo.entity.User;
import java.util.List;

public class UserWithRoles { private User user;
    private List<String> roles;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
