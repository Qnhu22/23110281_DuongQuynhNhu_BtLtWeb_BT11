package com.demo.security.service;

import com.demo.security.entity.UserInfo;
import com.demo.security.repository.UserInfoRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UserInfoDetailsService implements UserDetailsService {

    private final UserInfoRepository repository;

    public UserInfoDetailsService(UserInfoRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return User.withUsername(user.getUsername())
                .password(user.getPassword())   // password đã được encode trong DB
                .roles(user.getRoles())
                .build();
    }
}
