package com.capg.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.entity.UserAccount;
import com.capg.repository.IUserAccountRepository;
import com.capg.service.IUserAccountService;

/**
 * UserAccountServiceImpl (Service Implementation)
 * 
 * This class implements IUserAccountService and contains
 * the business logic for user account management.
 * 
 * Responsibilities:
 * - Register new user accounts
 * - Authenticate users (login)
 * - Update login details
 * 
 * It interacts with IUserAccountRepo to perform database operations.
 */
@Service
public class UserAccountServiceImpl implements IUserAccountService {

    /**
     * Repository for UserAccount entity
     */
    @Autowired
    private IUserAccountRepository repo;

    /**
     * Register a new user account
     * 
     * @param account UserAccount object containing user details
     * @return saved UserAccount object
     */
    @Override
    public UserAccount register(UserAccount account) {

        // Set default account status
        account.setStatus("ACTIVE");

        // Set current login/registration time
        account.setLoginDate(LocalDateTime.now());

        return repo.save(account);
    }

    /**
     * Authenticate user login using username and password
     * 
     * @param username user's username
     * @param password user's password
     * @return UserAccount object if login successful
     * @throws RuntimeException if credentials are invalid
     */
    @Override
    public UserAccount login(String username, String password) {

        Optional<UserAccount> userOpt = repo.findByUsernameAndPassword(username, password);

        if (userOpt.isPresent()) {

            UserAccount user = userOpt.get();

            // Update login time
            user.setLoginDate(LocalDateTime.now());

            return repo.save(user);

        } else {
            // Throw exception if login fails
            throw new RuntimeException("Invalid Username or Password!!");
        }
    }
}