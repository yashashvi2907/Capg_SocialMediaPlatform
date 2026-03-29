package com.capg.service.impl;

import com.capg.dto.CommentDto;
import com.capg.entity.Comment;
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
        return commentRepository.findByPostPostID(postId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}