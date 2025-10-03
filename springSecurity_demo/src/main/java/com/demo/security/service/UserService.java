package com.demo.security.service;

import com.demo.security.entity.UserInfo;
import com.demo.security.repository.UserInfoRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserInfoRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserInfoRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    // Thêm user mới -> encode password trước khi lưu
    public UserInfo addUser(UserInfo user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    // Lấy tất cả user
    public List<UserInfo> getAllUsers() {
        return repository.findAll();
    }
}
