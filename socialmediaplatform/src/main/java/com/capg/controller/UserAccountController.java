package com.capg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.capg.dto.LoginDTO;
import com.capg.entity.UserAccount;
import com.capg.security.JwtUtil;
import com.capg.service.IUserAccountService;


@RestController
@RequestMapping("/account")
public class UserAccountController {

    @Autowired
    private IUserAccountService service;
    
    @PostMapping("/login")
    public String login(@RequestBody LoginDTO dto) {

        UserAccount user = service.login(dto.getUsername(), dto.getPassword());

        String token = JwtUtil.generateToken(user.getUsername());

        return "Bearer " + token;
    }
}


















    
//  Register API
// @PostMapping("/register")
// public UserAccount register(@RequestBody UserAccount account) {
//     return service.register(account);
// }

// @PostMapping("/login")
// public String login(@RequestBody LoginDTO dto, HttpSession session) {
//
//     UserAccount user = service.login(dto.getUsername(), dto.getPassword());
//
//     session.setAttribute("loggedUser", user);
//
//     // Store login time
//     session.setAttribute("loginTime", System.currentTimeMillis());
//
//     // 1 minute session
//     session.setMaxInactiveInterval(60);
//
//     return "Login Successful!!  Welcome " + user.getUsername();
// }
    
    
//    public void checkLogin(HttpSession session) {
//        if (session.getAttribute("loggedUser") == null) {
//        	throw new UnauthorizedException("Please login first");
//        }
//    }
