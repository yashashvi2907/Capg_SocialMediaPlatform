package com.capg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.capg.entity.Group;

public interface GroupRepository extends JpaRepository<Group, Integer> {
}