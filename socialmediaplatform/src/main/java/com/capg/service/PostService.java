package com.capg.service;

import java.time.LocalDateTime;
import java.util.List;
import com.capg.dto.PostDTO;

/**
 * Service interface for managing post-related operations.
 * <p>
 * Defines methods for retrieving, filtering, searching,
 * and generating post feeds.
 * </p>
 */
public interface PostService {

    /**
     * Retrieves posts created by a specific user.
     *
     * @param userId the unique identifier of the user
     * @return list of PostDto representing user's posts
     */
    List<PostDTO> getPostsByUser(Integer userId);

    /**
     * Retrieves posts within a specified date range.
     *
     * @param start the start date-time (inclusive)
     * @param end   the end date-time (inclusive)
     * @return list of PostDto within the given date range
     */
    List<PostDTO> getPostsByDateRange(LocalDateTime start, LocalDateTime end);

    /**
     * Searches posts containing a specific keyword.
     *
     * @param keyword the keyword to search in post content
     * @return list of PostDto matching the keyword
     */
    List<PostDTO> searchPosts(String keyword);

    /**
     * Retrieves trending posts.
     * <p>
     * Trending criteria may include recency, likes,
     * comments, or engagement metrics.
     * </p>
     *
     * @return list of trending PostDto
     */
    List<PostDTO> getTrendingPosts();

    /**
     * Generates a personalized feed for a user.
     * <p>
     * Includes posts from the user and their connections,
     * typically sorted by recency or engagement.
     * </p>
     *
     * @param userId the unique identifier of the user
     * @return list of PostDto representing the user's feed
     */
    List<PostDTO> getFeed(Integer userId);
}