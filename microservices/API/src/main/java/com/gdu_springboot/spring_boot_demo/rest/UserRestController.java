package com.gdu_springboot.spring_boot_demo.rest;

import com.gdu_springboot.spring_boot_demo.entity.User;
import com.gdu_springboot.spring_boot_demo.service.UserService;
import org.springframework.web.bind.annotation.*;
import com.gdu_springboot.spring_boot_demo.dto.UserWithRoles;


import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    // Lấy tất cả users
    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    // Lấy user theo username
    @GetMapping("/users/{username}")
    public User getUser(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    // Thêm user mới
    @PostMapping("/users")
    public String addUser(@RequestBody UserWithRoles userWithRoles) {
        userService.save(userWithRoles.getUser(), userWithRoles.getRoles());
        return "User created: " + userWithRoles.getUser().getUsername();
    }

    // Cập nhật user (dùng lại hàm save)
    @PutMapping("/users")
    public String updateUser(@RequestBody UserWithRoles userWithRoles) {
        userService.save(userWithRoles.getUser(), userWithRoles.getRoles());
        return "User updated: " + userWithRoles.getUser().getUsername();
    }

    // Xóa user
    @DeleteMapping("/users/{username}")
    public String deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return "User deleted: " + username;
    }

    // Cập nhật quyền role cho user
    @PutMapping("/users/{username}/roles")
    public String updateUserRole(@PathVariable String username,
                                 @RequestParam String role,
                                 @RequestParam boolean hasRole) {
        userService.updateUserRole(username, role, hasRole);
        return "Updated role '" + role + "' for user: " + username;
    }
}
