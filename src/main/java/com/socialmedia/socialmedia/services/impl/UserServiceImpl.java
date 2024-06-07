package com.socialmedia.socialmedia.services.impl;

import com.socialmedia.socialmedia.entities.User;
import com.socialmedia.socialmedia.repositories.UserRepository;
import com.socialmedia.socialmedia.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

//-----------------------------------COMMON---------------------------------------------

    @Override
    public User findUserById(Integer userId) {
        User user = userRepository.findUserById(userId);
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException("User not found with ID: " + userId);
    }

//---------------------------------GET ALL USER--------------------------------------------

    @Override
    public List<User> allUsers() {
        return new ArrayList<>(userRepository.findAll());
    }


//---------------------------------SEARCH USER--------------------------------------------

    @Override
    public List<User> searchUser(String query) {
        return userRepository.findByUserNameContainingOrEmailContaining(query, query);
    }

    //---------------------------------UPDATE USER--------------------------------------------
    @Override
    public User updateUser(User user) {
        Optional<User> userUpdateOpt = userRepository.findByEmail(user.getEmail());
        return userUpdateOpt
                .map(existingUser -> updateExistingUser(user, existingUser))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private User updateExistingUser(User user, User existingUser) {
        existingUser.setUserName(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(existingUser);
    }

//---------------------------------DELETE USER--------------------------------------------

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new EntityNotFoundException("User not found with the id: " + userId);
        }
        else {
            userRepository.deleteById(userId);
        }
    }

    //---------------------------------FOLLOW USER--------------------------------------------
    @Override
    public User followUser(Integer userId1, Integer userId2) {
        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);
        user1.getFollowers().add(user2.getId());
        user2.getFollowers().add(user1.getId());

        userRepository.save(user1);
        userRepository.save(user2);
        return user1;
    }
}
