package com.capg.service.impl;

import com.capg.dto.CommentDto;
import com.capg.entity.Comment;
import com.capg.exception.BadRequestException;
import com.capg.exception.CommentNotFoundException;
import com.capg.repository.CommentRepository;
import com.capg.service.CommentService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for handling comment-related operations.
 * Provides business logic for retrieving comments associated with posts.
 */
@Service
public class CommentServiceImpl implements CommentService {

    /**
     * Repository for accessing comment data.
     */
    private final CommentRepository commentRepository;

    /**
     * Constructor-based dependency injection for CommentRepository.
     * @param commentRepository repository for comment data access
     */
    public CommentServiceImpl(final CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    /**
     * Converts Comment entity to CommentDto.
     * @param comment Comment entity
     * @return mapped CommentDto
     */
    private CommentDto mapToDTO(final Comment comment) {
        final CommentDto dto = new CommentDto();

        dto.setCommentID(comment.getCommentID());
        dto.setCommentText(comment.getCommentText());
        dto.setTimestamp(comment.getTimestamp());

        dto.setUserID(comment.getUser().getUserID());
        dto.setUsername(comment.getUser().getUsername());

        dto.setPostID(comment.getPost().getPostID());

        return dto;
    }

    /**
     * Retrieves all comments associated with a specific post.
     * @param postId unique identifier of the post
     * @return list of CommentDto representing comments
     * @throws BadRequestException if postId is null
     * @throws CommentNotFoundException if no comments are found
     */
    @Override
    public List<CommentDto> getCommentsByPost(final Integer postId) {

        // Validation
        if (postId == null) {
            throw new BadRequestException("Post ID cannot be null");
        }

        final List<Comment> comments = commentRepository.findByPostPostID(postId);

        // Not Found
        if (comments.isEmpty()) {
            throw new CommentNotFoundException("No comments found for post: " + postId);
        }

        return comments.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}