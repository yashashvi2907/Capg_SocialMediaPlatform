package com.capg.repository;

import com.capg.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    // Proper naming 
    List<Post> findByUserUserID(Integer userID);

    // Date range
    List<Post> findByTimestampBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Keyword search 
    List<Post> findByContentContainingIgnoreCase(String keyword);

}