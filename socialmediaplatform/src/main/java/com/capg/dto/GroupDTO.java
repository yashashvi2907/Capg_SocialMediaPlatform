package com.capg.dto;

/**
 * Data Transfer Object (DTO) class for representing Group data.
 * This class encapsulates group-related information along with
 * administrator details such as ID, username, and email.
 * It is used to transfer data between layers of the application.
 */
public class GroupDTO {

    /** Unique identifier for the group. */
    private Integer groupID;

    /** Name of the group. */
    private String groupName;

    /** Unique identifier of the group administrator. */
    private Integer adminID;

    /** Username of the group administrator. */
    private String adminUsername;

    /** Email address of the group administrator. */
    private String adminEmail;

    /**
     * Creates a GroupDTO with group and admin details.
     * @param groupID group ID
     * @param groupName group name
     * @param adminID admin ID
     * @param adminUsername admin username
     * @param adminEmail admin email
     */
    public GroupDTO(Integer groupID, String groupName,
                    Integer adminID, String adminUsername, String adminEmail) {
        this.groupID = groupID;
        this.groupName = groupName;
        this.adminID = adminID;
        this.adminUsername = adminUsername;
        this.adminEmail = adminEmail;
    }

    /**
     * Retrieves the group ID.
     * @return the unique identifier of the group
     */
    public Integer getGroupID() {
        return groupID;
    }

    /**
     * Retrieves the group name.
     * @return the name of the group
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Retrieves the admin ID.
     * @return the unique identifier of the admin
     */
    public Integer getAdminID() {
        return adminID;
    }

    /**
     * Retrieves the admin username.
     * @return the username of the admin
     */
    public String getAdminUsername() {
        return adminUsername;
    }

    /**
     * Retrieves the admin email.
     * @return the email address of the admin
     */
    public String getAdminEmail() {
        return adminEmail;
    }
}