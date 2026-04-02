package com.capg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.capg.entity.UserAccount;

public interface IUserAccountRepo extends JpaRepository<UserAccount, Integer> {

    Optional<UserAccount> findByUsername(String username);
    Optional<UserAccount> findByUsernameAndPassword(String username, String password);
}