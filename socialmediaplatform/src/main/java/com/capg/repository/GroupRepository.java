package com.capg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.capg.entity.Group;

/**
 * Repository interface for Group entity.
 * Provides basic CRUD operations for Group.
 */
public interface GroupRepository extends JpaRepository<Group, Integer> {
}