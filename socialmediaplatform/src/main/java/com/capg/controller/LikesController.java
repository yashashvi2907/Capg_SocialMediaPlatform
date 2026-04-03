package com.capg.controller;

import com.capg.dto.LikesDTO;
import com.capg.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controller class for handling Likes related APIs.
 */
@RestController
@RequestMapping("/api/likes")
public class LikesController {
    /**
     * Fetch all likes for a given post ID.
     *
     * @param postId the ID of the post
     * @return list of LikesDTO if present, otherwise 204 No Content
     */
    @Autowired
    private LikesService likeService;

    /**
     * GET API: Get Users Who Liked a Post
     * URL: /api/likes/post/{postId}
     * Returns a list of LikeDto objects containing user info who liked the post
     */
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<LikesDTO>> getLikesByPost(@PathVariable Integer postId) {
        List<LikesDTO> likes = likeService.getLikesByPost(postId);
        if (likes.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content if no likes
        }
        return ResponseEntity.ok(likes);// 200 OK with list of likes
    }
}