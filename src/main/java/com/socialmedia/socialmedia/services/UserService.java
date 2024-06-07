package com.socialmedia.socialmedia.services;

import com.socialmedia.socialmedia.entities.User;

import java.util.List;

public interface UserService {

    List<User> allUsers();

    User findUserById(Integer userId);

    List<User> searchUser(String query);

    User updateUser(User user);

    void deleteUser(Integer userId);

    User followUser(Integer userId1, Integer userId2);
}
