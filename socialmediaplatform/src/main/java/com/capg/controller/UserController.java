package com.capg.controller;

import com.capg.dto.PostDto;
import com.capg.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for handling user-related operations.
 * Provides APIs to retrieve user posts.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * Service layer for user-related operations.
     */
    private final UserService userService;

    /**
     * Constructor-based dependency injection for UserService.
     *
     * @param userService service layer for user operations
     */
    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves all posts created by a specific user.
     *
     * @param userId unique identifier of the user
     * @return list of PostDto representing user posts
     */
    @GetMapping("/{userId}/posts")
    public List<PostDto> getUserPosts(@PathVariable final Integer userId) {
        return userService.getUserPosts(userId);
    }
}