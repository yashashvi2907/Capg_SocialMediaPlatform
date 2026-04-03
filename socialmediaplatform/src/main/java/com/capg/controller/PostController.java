package com.capg.controller;

import com.capg.dto.PostDto;
import com.capg.service.PostService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * REST Controller for handling post-related operations.
 * <p>
 * Provides APIs for retrieving trending posts, user-specific posts,
 * filtering posts by date range, and searching posts by keyword.
 * </p>
 */
@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    /**
     * Constructor-based dependency injection for PostService.
     *
     * @param postService the service layer handling post operations
     */
    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * Retrieves trending posts.
     * <p>
     * Endpoint: GET /api/posts/trending
     * </p>
     *
     * @return ResponseEntity containing list of trending PostDto objects
     */
    @GetMapping("/trending")
    public ResponseEntity<List<PostDto>> getTrendingPosts() {
        return ResponseEntity.ok(postService.getTrendingPosts());
    }

    /**
     * Retrieves all posts created by a specific user.
     * <p>
     * Endpoint: GET /api/posts/user/{userId}
     * </p>
     *
     * @param userId the unique identifier of the user
     * @return ResponseEntity containing list of PostDto objects for the user
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(postService.getPostsByUser(userId));
    }

    /**
     * Retrieves posts within a specified date range.
     * <p>
     * Endpoint: GET /api/posts/date?start={startDateTime}&end={endDateTime}
     * </p>
     *
     * @param start the start date-time (inclusive)
     * @param end   the end date-time (inclusive)
     * @return ResponseEntity containing list of PostDto objects within the date range
     */
    @GetMapping("/date")
    public ResponseEntity<List<PostDto>> getPostsByDateRange(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {

        return ResponseEntity.ok(postService.getPostsByDateRange(start, end));
    }

    /**
     * Searches posts based on a keyword.
     * <p>
     * Endpoint: GET /api/posts/search?keyword={keyword}
     * </p>
     *
     * @param keyword the keyword used to search posts
     * @return ResponseEntity containing list of matching PostDto objects
     */
    @GetMapping("/search")
    public ResponseEntity<List<PostDto>> searchPosts(@RequestParam String keyword) {
        return ResponseEntity.ok(postService.searchPosts(keyword));
    }
}