package com.capg.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.entity.UserAccount;
import com.capg.repository.IUserAccountRepo;
import com.capg.service.IUserAccountService;

@Service
public class UserAccountServiceImpl implements IUserAccountService {

    @Autowired
    private IUserAccountRepo repo;

    @Override
    public UserAccount register(UserAccount account) {
        account.setStatus("ACTIVE");
        account.setLoginDate(LocalDateTime.now());
        return repo.save(account);
    }

    @Override
    public UserAccount login(String username, String password) {

        Optional<UserAccount> userOpt = repo.findByUsernameAndPassword(username, password);

        if (userOpt.isPresent()) {
            UserAccount user = userOpt.get();
            user.setLoginDate(LocalDateTime.now());
            return repo.save(user);
        } else {
            throw new RuntimeException("Invalid Username or Password!!");
        }
    }
    
    
}