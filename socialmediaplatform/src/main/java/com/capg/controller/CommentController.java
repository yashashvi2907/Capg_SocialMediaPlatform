package com.capg.controller;

import com.capg.dto.CommentDto;
import com.capg.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @GetMapping("/posts/{id}")
    public String getComments(@PathVariable int id) {
        return "Comments for post " + id;
    }
}
