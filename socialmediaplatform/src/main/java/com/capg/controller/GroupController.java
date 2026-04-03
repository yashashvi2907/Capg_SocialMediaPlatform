package com.capg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capg.dto.GroupDTO;
import com.capg.service.GroupService;

/**
 * Controller class for managing Group APIs.
 * Handles operations like fetching groups.
 */
@RestController
@RequestMapping("/api/groups")
public class GroupController {
    /**
     * Service layer dependency for handling group operations.
     */
    @Autowired
    private GroupService groupService;

    /**
     * Fetch all groups.
     *
     * @return list of GroupDTO
     */
    @GetMapping
    public ResponseEntity<List<GroupDTO>> getAllGroups() {
        List<GroupDTO> groups = groupService.getAllGroups();

        if (groups.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204
        }

        return ResponseEntity.ok(groups); // 200
    }

    /**
     * Fetch a group by its ID.
     *
     * @param id group ID
     * @return GroupDTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> getGroupById(@PathVariable Integer id) {
        GroupDTO group = groupService.getGroupById(id);
        return ResponseEntity.ok(group); // Exception handled globally
    }
}