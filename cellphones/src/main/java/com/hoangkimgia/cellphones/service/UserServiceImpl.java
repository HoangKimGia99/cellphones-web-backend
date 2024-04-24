package com.hoangkimgia.cellphones.service;

import com.hoangkimgia.cellphones.entity.User;

import java.util.List;

public interface UserServiceImpl {
    void createUser(User user);
    List<User> getUsers();
    void deleteUser(String userId);
    User getUserId(String userId);
}
