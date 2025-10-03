package com.demo.security.service;

import com.demo.security.entity.Users;
import com.demo.security.model.UserRegistrationDto;

import java.util.List;

public interface UserServiceV2 {
    Users registerUser(UserRegistrationDto dto);
    List<Users> getAllUsers();
}
