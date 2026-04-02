package com.capg.service.impl;

import com.capg.dto.CommentDto;
import com.capg.entity.Comment;
import com.capg.Exception.BadRequestException;
import com.capg.Exception.CommentNotFoundException;
import com.capg.repository.CommentRepository;
import com.capg.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    private CommentDto mapToDTO(Comment comment) {
        CommentDto dto = new CommentDto();

        dto.setCommentID(comment.getCommentID());
        dto.setCommentText(comment.getCommentText());
        dto.setTimestamp(comment.getTimestamp());

        dto.setUserID(comment.getUser().getUserID());
        dto.setUsername(comment.getUser().getUsername());

        dto.setPostID(comment.getPost().getPostID());

        return dto;
    }

    @Override
    public List<CommentDto> getCommentsByPost(Integer postId) {

        //Validation
        if (postId == null) {
            throw new BadRequestException("Post ID cannot be null");
        }

        List<Comment> comments = commentRepository.findByPostPostID(postId);

        //Not Found
        if (comments.isEmpty()) {
            throw new CommentNotFoundException("No comments found for post: " + postId);
        }

        return comments.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}