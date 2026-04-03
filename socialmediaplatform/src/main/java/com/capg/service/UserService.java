package com.capg.service;

import com.capg.dto.PostDto;

import java.util.List;

/**
 * Service interface for handling user-related operations.
 * Defines business logic for retrieving user posts.
 */
public interface UserService {

    /**
     * Retrieves all posts created by a specific user.
     *
     * @param userId unique identifier of the user
     * @return list of PostDto representing user posts
     */
    List<PostDto> getUserPosts(Integer userId);

}