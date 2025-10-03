package com.demo.demo4.service;

import com.demo.demo4.model.AuthResponse;
import com.demo.demo4.model.LoginRequest;
import com.demo.demo4.model.RegisterRequest;

public interface UserService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
