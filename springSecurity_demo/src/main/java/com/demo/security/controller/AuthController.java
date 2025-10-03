package com.demo.security.controller;

import com.demo.security.entity.Users;
import com.demo.security.model.UserRegistrationDto;
import com.demo.security.repository.RoleRepository;
import com.demo.security.service.UserServiceV2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
public class AuthController {

    private final UserServiceV2 userService;
    private final RoleRepository roleRepository;

    public AuthController(UserServiceV2 userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        model.addAttribute("roles",
                roleRepository.findAll().stream()
                        .map(r -> r.getName().replace("ROLE_", ""))
                        .collect(Collectors.toList()));
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute("user") UserRegistrationDto dto, Model model) {
        try {
            userService.registerUser(dto);
            return "redirect:/login?success";
        } catch (RuntimeException ex) {
            model.addAttribute("user", dto);
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("roles",
                    roleRepository.findAll().stream()
                            .map(r -> r.getName().replace("ROLE_", ""))
                            .collect(Collectors.toList()));
            return "register";
        }
    }
}


