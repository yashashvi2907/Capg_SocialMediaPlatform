package com.capg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.capg.entity.UserAccount;

/**
 * IUserAccountRepo (Repository Interface)
 * 
 * This interface is responsible for performing database operations
 * related to UserAccount entity.
 * 
 * It extends JpaRepository, which provides:
 * - Basic CRUD operations (save, findById, delete, findAll)
 * - Built-in support for pagination and sorting
 * 
 * Custom query methods are defined using Spring Data JPA naming conventions.
 */
public interface IUserAccountRepo extends JpaRepository<UserAccount, Integer> {

    /**
     * Find user account by username
     * 
     * @param username unique username of the user
     * @return Optional containing UserAccount if found, otherwise empty
     */
    Optional<UserAccount> findByUsername(String username);

    /**
     * Find user account by username and password (used for login)
     * 
     * @param username user's username
     * @param password user's password
     * @return Optional containing UserAccount if credentials match, otherwise empty
     */
    Optional<UserAccount> findByUsernameAndPassword(String username, String password);
}