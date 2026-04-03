package com.capg.repository;

import com.capg.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for Likes entity.
 * Provides methods to perform CRUD and custom queries on Likes.
 */
public interface LikesRepository extends JpaRepository<Likes, Integer> {

    /**
     * Retrieves all likes associated with a specific post.
     *
     * @param postID ID of the post
     * @return list of likes for the given post
     */
    List<Likes> findByPostPostID(Integer postID);

    /**
     * Retrieves all likes made by a specific user.
     *
     * @param userID ID of the user
     * @return list of likes by the given user
     */
    List<Likes> findByUserUserID(Integer userID);
}