package com.capg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.capg.dto.LoginDTO;
import com.capg.entity.UserAccount;
import com.capg.security.JwtUtil;
import com.capg.service.IUserAccountService;

/**
 * UserAccountController
 * 
 * This controller handles user authentication APIs such as login.
 * 
 * Responsibilities:
 * - Authenticate user credentials
 * - Generate JWT token upon successful login
 * 
 * Base URL: /account
 */
@RestController
@RequestMapping("/account")
public class UserAccountController {

    /**
     * Service layer for user account operations
     */
    @Autowired
    private IUserAccountService service;
    
    /**
     * Login API
     * 
     * This API authenticates the user using username and password.
     * If credentials are valid, a JWT token is generated and returned.
     * 
     * @param dto LoginDTO containing username and password
     * @return JWT token in "Bearer <token>" format
     */
    @PostMapping("/login")
    public String login(@RequestBody LoginDTO dto) {

        // Authenticate user
        UserAccount user = service.login(dto.getUsername(), dto.getPassword());

        // Generate JWT token
        String token = JwtUtil.generateToken(user.getUsername());

        // Return token with Bearer prefix
        return "Bearer " + token;
    }
}