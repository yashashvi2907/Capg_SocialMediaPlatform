package com.capg.controller;

import com.capg.dto.CommentDto;
import com.capg.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing comment-related operations.
 * Provides APIs to retrieve comments associated with posts.
 */
@RestController
@RequestMapping("/api/comments")
public class CommentController {


    /**
     * Constructor-based dependency injection for CommentService.
     * @param commentService service layer for comment operations
     */
    @Autowired
    private CommentService commentService;

    /**
     * Retrieves all comments associated with a specific post.
     * @param postId unique identifier of the post
     * @return ResponseEntity containing list of CommentDto
     */
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentsByPost(@PathVariable Integer postId) {
        return ResponseEntity.ok(commentService.getCommentsByPost(postId));
    }
}