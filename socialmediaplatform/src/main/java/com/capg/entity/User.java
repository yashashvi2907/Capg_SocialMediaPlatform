package com.capg.entity;

import jakarta.persistence.*;
import java.util.List;

/**
 * Entity representing a user in the system.
 * Maps to the "users" table and maintains relationships with other entities.
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * Unique identifier of the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
    private Integer userID;

    /**
     * Username of the user.
     */
    @Column(name = "username")
    private String username;

    /**
     * Email address of the user.
     */
    @Column(name = "email")
    private String email;

    /**
     * Password of the user.
     */
    @Column(name = "password")
    private String password;

    /**
     * Profile picture of the user.
     */
    @Lob
    @Column(name = "profile_picture")
    private byte[] profilePicture;

    /**
     * List of posts created by the user.
     */
    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    /**
     * List of comments made by the user.
     */
    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    /**
     * List of likes by the user.
     */
    @OneToMany(mappedBy = "user")
    private List<Likes> likes;

    /**
     * List of notifications for the user.
     */
    @OneToMany(mappedBy = "user")
    private List<Notification> notifications;

    /**
     * Messages sent by the user.
     */
    @OneToMany(mappedBy = "sender")
    private List<Message> sentMessages;

    /**
     * Messages received by the user.
     */
    @OneToMany(mappedBy = "receiver")
    private List<Message> receivedMessages;

    /**
     * Friend relationships (as first user).
     */
    @OneToMany(mappedBy = "user1")
    private List<Friends> friends1;

    /**
     * User account associated with this user.
     */
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserAccount userAccount;

    /**
     * Default constructor.
     */
    public User() {
        // default constructor
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(final Integer userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public byte[] getProfilePicture() {
        return profilePicture != null ? profilePicture.clone() : null;
    }

    public void setProfilePicture(final byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(final List<Post> posts) {
        this.posts = posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(final List<Comment> comments) {
        this.comments = comments;
    }

    public List<Likes> getLikes() {
        return likes;
    }

    public void setLikes(final List<Likes> likes) {
        this.likes = likes;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(final List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<Message> getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages(final List<Message> sentMessages) {
        this.sentMessages = sentMessages;
    }

    public List<Message> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(final List<Message> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }

    public List<Friends> getFriends1() {
        return friends1;
    }

    public void setFriends1(final List<Friends> friends1) {
        this.friends1 = friends1;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(final UserAccount userAccount) {
        this.userAccount = userAccount;
    }
}