package com.arrkadique.bankingsystem.controller;

import com.arrkadique.bankingsystem.entity.User;
import com.arrkadique.bankingsystem.service.DefaultEmailService;
import com.arrkadique.bankingsystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/registration")
@Slf4j
public class RegistrationController {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final DefaultEmailService emailService;

    public RegistrationController(PasswordEncoder passwordEncoder, UserService userService, DefaultEmailService emailService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping
    public String registration(){
        return "registration";
    }

    @PostMapping()
    public String addUser(String username, String lastName, String phoneNumber, String password, String email)
    {
        int code = new Random().nextInt(8999)+1000;
        try {
            emailService.sendSimpleEmail(email, "Welcome", String.valueOf(code));
        } catch (Exception ignored) {
        }
        User user = new User();
        user.setUsername(username);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setCode(String.valueOf(code));
        user.setPhoneNumber(phoneNumber);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoleId(1L);

        userService.createUser(user);

        return "redirect:/login";
    }

}
