package com.capg.repository;

import com.capg.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // optional - just added
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}