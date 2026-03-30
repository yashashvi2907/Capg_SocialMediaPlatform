package com.capg.repository;

import com.capg.entity.Friends;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendsRepository extends JpaRepository<Friends, Integer> {

    // Get accepted friendships where user is involved
    List<Friends> findByUser1UserIDAndStatus(Integer userId, String status);

    List<Friends> findByUser2UserIDAndStatus(Integer userId, String status);
}