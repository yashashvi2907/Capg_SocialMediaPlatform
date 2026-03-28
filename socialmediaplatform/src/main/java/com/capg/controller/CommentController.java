package com.capg.controller;

import com.capg.dto.CommentDto;
import com.capg.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // GET comments by postId
    @GetMapping("/post/{postId}")
    public List<CommentDto> getCommentsByPost(@PathVariable Integer postId) {
        return commentService.getCommentsByPost(postId);
    }
}