package com.capg.service;

import java.util.List;
import com.capg.dto.CommentDto;

/**
 * Service interface for handling comment-related operations.
 * Defines business logic for retrieving comments.
 */
public interface CommentService {

    /**
     * Retrieves all comments associated with a specific post.
     * @param postId unique identifier of the post
     * @return list of CommentDto representing comments
     */
    List<CommentDto> getCommentsByPost(Integer postId);
}