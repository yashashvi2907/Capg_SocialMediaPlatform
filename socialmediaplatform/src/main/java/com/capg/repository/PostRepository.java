package com.capg.repository;

import com.capg.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    // Proper naming (clean FK traversal)
    List<Post> findByUserUserID(Integer userID);

    // Date range
    List<Post> findByTimestampBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Keyword search (derived not possible → keep simple JPQL)
    List<Post> findByContentContainingIgnoreCase(String keyword);

}