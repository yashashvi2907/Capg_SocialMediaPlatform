package com.capg.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * UserAccount Entity
 *
 * This class represents the account details of a user in the system.
 * It is mainly used for authentication and login management.
 *
 * Table Name: user_account
 *
 * Description:
 * - Stores login credentials and account status
 * - Maintains last login time
 * - Linked with User entity (One-to-One relationship)
 *
 * Fields:
 * - accountId  : Unique account ID (Primary Key)
 * - username   : Username of the account
 * - password   : Password (cannot be null)
 * - loginDate  : Last login timestamp
 * - status     : Account status (ACTIVE / INACTIVE)
 * - user       : Associated User entity
 */
@Entity
@Table(name = "user_account")
public class UserAccount {

    /**
     * Primary Key - Unique Account ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="account_id")
    private Integer accountId;

    /**
     * Username of the account
     */
    private String username;

    /**
     * Password of the account (mandatory field)
     */
    @Column(nullable = false)
    private String password;

    /**
     * Last login date and time
     */
    @Column(name="login_date")
    private LocalDateTime loginDate;

    /**
     * Account status (ACTIVE / INACTIVE)
     */
    @Column(nullable = false)
    private String status;


    /**
     * Get Account ID
     * @return accountId
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * Set Account ID
     * @param accountId unique ID
     */
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    /**
     * Get Username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set Username
     * @param username user's username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get Password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set Password
     * @param password user's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get Login Date
     * @return loginDate
     */
    public LocalDateTime getLoginDate() {
        return loginDate;
    }

    /**
     * Set Login Date
     * @param loginDate last login time
     */
    public void setLoginDate(LocalDateTime loginDate) {
        this.loginDate = loginDate;
    }

    /**
     * Get Account Status
     * @return status (ACTIVE / INACTIVE)
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set Account Status
     * @param status account status
     */
    public void setStatus(String status) {
        this.status = status;
    }

}