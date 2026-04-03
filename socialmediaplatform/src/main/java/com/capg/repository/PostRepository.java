package com.capg.repository;

import com.capg.dto.PostDto;
import com.capg.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository interface for managing Post entities.
 * <p>
 * Provides CRUD operations along with custom query methods
 * for filtering and searching posts.
 * </p>
 */
public interface PostRepository extends JpaRepository<Post, Integer> {

    /**
     * Retrieves all posts created by a specific user.
     *
     * @param userID the unique identifier of the user
     * @return list of Post entities belonging to the user
     */
    List<Post> findByUserUserID(Integer userID);

    /**
     * Retrieves posts within a specified date range.
     *
     * @param startDate the start timestamp (inclusive)
     * @param endDate   the end timestamp (inclusive)
     * @return list of Post entities within the given date range
     */
    List<Post> findByTimestampBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Searches posts containing a specific keyword (case-insensitive).
     * <p>
     * This query:
     * <ul>
     *     <li>Filters posts based on keyword match in content</li>
     *     <li>Joins with User entity to fetch user details</li>
     *     <li>Counts likes and comments using SIZE()</li>
     *     <li>Maps result directly to PostDto</li>
     * </ul>
     *
     * @param keyword the keyword to search in post content
     * @return list of PostDto containing matched posts
     */
    @Query("SELECT new com.capg.dto.PostDto(" +
            "p.postID, p.content, p.timestamp, " +
            "u.userID, u.username, " +
            "SIZE(p.likes), SIZE(p.comments)) " +
            "FROM Post p JOIN p.user u " +
            "WHERE LOWER(p.content) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<PostDto> searchPostsByKeyword(@Param("keyword") String keyword);

}