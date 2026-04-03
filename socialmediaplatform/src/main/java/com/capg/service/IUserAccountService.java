package com.capg.service;

import com.capg.entity.UserAccount;

/**
 * IUserAccountService (Service Interface)
 * 
 * This interface defines all business operations related to
 * User Account management in the Social Media Platform.
 * 
 * It acts as a bridge between Controller and Repository layers.
 * 
 * Responsibilities:
 * - Register new user
 * - Authenticate user (login)
 */
public interface IUserAccountService {

    /**
     * Register a new user account
     * 
     * @param account UserAccount object containing user details
     * @return saved UserAccount object
     */
    UserAccount register(UserAccount account);

    /**
     * Authenticate user login using username and password
     * 
     * @param username user's username
     * @param password user's password
     * @return UserAccount object if login successful
     */
    UserAccount login(String username, String password);
}