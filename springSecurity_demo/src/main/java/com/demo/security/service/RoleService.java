package com.demo.security.service;

import com.demo.security.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> getAllRoles();
    Optional<Role> findByName(String name);
}
