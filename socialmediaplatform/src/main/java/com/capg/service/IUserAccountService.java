package com.capg.service;

import com.capg.entity.UserAccount;

public interface IUserAccountService {

    UserAccount register(UserAccount account);

    UserAccount login(String username, String password);
}