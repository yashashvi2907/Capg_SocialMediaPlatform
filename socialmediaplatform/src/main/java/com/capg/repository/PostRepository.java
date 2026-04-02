package com.capg.repository;

import com.capg.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    //Find Post of a user
    List<Post> findByUserUserID(Integer userID);

    //Find Post by date range
    List<Post> findByTimestampBetween(LocalDateTime startDate, LocalDateTime endDate);

    //Find Post by Keyword
    List<Post> findByContentContainingIgnoreCase(String keyword);

}