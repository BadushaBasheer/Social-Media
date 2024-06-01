package com.socialmedia.socialmedia.services;

import com.socialmedia.socialmedia.entities.User;
import com.socialmedia.socialmedia.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> allUsers() {
        return new ArrayList<>(userRepository.findAll());
    }
}
