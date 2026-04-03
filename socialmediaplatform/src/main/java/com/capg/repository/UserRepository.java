package com.capg.repository;

import com.capg.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing User entities.
 * Provides database access methods for user-related operations.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Retrieves a user by username.
     *
     * @param username username of the user
     * @return Optional containing User if found, otherwise empty
     */
    Optional<User> findByUsername(final String username);

    /**
     * Retrieves a user by email.
     *
     * @param email email of the user
     * @return Optional containing User if found, otherwise empty
     */
    Optional<User> findByEmail(final String email);
}