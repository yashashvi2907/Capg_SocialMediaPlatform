package com.capg.repository;

import com.capg.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Likes, Integer> {

    //Get likes by post
    List<Likes> findByPostPostID(Integer postID);

    //Get likes by user
    List<Likes> findByUserUserID(Integer userID);

}