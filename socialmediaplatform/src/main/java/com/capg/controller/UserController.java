package com.capg.controller;

import com.capg.dto.PostDTO;
import com.capg.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}/posts")
    public List<PostDTO> getUserPoscts(@PathVariable Integer userId) {
        return userService.getUserPosts(userId);
    }
}