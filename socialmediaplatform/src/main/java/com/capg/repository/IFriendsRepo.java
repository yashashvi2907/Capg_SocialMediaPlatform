package com.capg.repository;

import com.capg.entity.Friends;
import com.capg.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IFriendsRepo extends JpaRepository<Friends, Integer> {

    List<Friends> findByUser2AndStatus(User user2, String status);

    List<Friends> findByUser1OrUser2(User user1, User user2);
}