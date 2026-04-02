package com.capg.repository;

import com.capg.dto.PostDto;
import com.capg.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    //Find Post of a user
    List<Post> findByUserUserID(Integer userID);

    //Find Post by date range
    List<Post> findByTimestampBetween(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT new com.capg.dto.PostDto(" +
            "p.postID, p.content, p.timestamp, " +
            "u.userID, u.username, " +
            "SIZE(p.likes), SIZE(p.comments)) " +
            "FROM Post p JOIN p.user u " +
            "WHERE LOWER(p.content) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<PostDto> searchPostsByKeyword(@Param("keyword") String keyword);

}