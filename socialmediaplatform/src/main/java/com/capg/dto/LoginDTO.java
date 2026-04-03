package com.capg.dto;

/**
 * LoginDTO (Data Transfer Object)
 * 
 * This class is used to transfer login credentials from the client
 * to the server during authentication.
 * 
 * It contains:
 * - Username
 * - Password
 * 
 * Used in:
 * - UserAccountController (login API)
 */
public class LoginDTO {

    /**
     * Username of the user
     */
    private String username;

    /**
     * Password of the user
     */
    private String password;

    /**
     * Get Username
     * 
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set Username
     * 
     * @param username user's username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get Password
     * 
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set Password
     * 
     * @param password user's password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}