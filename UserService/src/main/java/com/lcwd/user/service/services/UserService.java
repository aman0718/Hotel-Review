package com.lcwd.user.service.services;

import java.util.List;

import com.lcwd.user.service.entities.User;

public interface UserService {

    User createUser(User user);

    User getUserById(String userId);

    List<User> getAllUsers();

}
