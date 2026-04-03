package com.capg.repository;

import com.capg.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing Comment entities.
 * Provides database operations related to comments.
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    /**
     * Retrieves all comments associated with a specific post.
     * @param postID unique identifier of the post
     * @return list of Comment entities for the given post
     */
    List<Comment> findByPostPostID(final Integer postID);

    /**
     * Retrieves all comments created by a specific user.
     * @param userID unique identifier of the user
     * @return list of Comment entities for the given user
     */
    List<Comment> findByUserUserID(final Integer userID);
}