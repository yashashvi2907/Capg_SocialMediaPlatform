package com.capg.repository;

import com.capg.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    //Get comments by post (FK traversal - clean naming)
    List<Comment> findByPostPostID(Integer postID);

    //Get comments by user
    List<Comment> findByUserUserID(Integer userID);

}