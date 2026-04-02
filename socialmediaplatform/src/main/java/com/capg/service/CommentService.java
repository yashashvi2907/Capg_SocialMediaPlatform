package com.capg.service;


import java.util.List;
import com.capg.dto.CommentDto;

public interface CommentService {
    List<CommentDto> getCommentsByPost(Integer postId);
}
