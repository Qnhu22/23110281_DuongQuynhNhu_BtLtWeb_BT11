package com.demo.security.controller;

import com.demo.security.entity.UserInfo;
import com.demo.security.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // API thêm user (POST bằng Postman)
    @PostMapping("/add")
    public ResponseEntity<UserInfo> addUser(@RequestBody UserInfo user) {
        return ResponseEntity.ok(service.addUser(user));
    }

    // API GET demo (gõ trực tiếp trên browser)
    @GetMapping("/add-demo")
    public ResponseEntity<String> addDemoUser() {
        UserInfo demo = new UserInfo();
        demo.setUsername("demo");
        demo.setPassword("demo123");
        demo.setRoles("USER");
        service.addUser(demo);
        return ResponseEntity.ok("✅ Demo user created: username=demo, password=demo123, role=USER");
    }

    // API lấy tất cả user
    @GetMapping("/all")
    public ResponseEntity<List<UserInfo>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }
}
