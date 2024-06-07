package com.socialmedia.socialmedia.repositories;

import com.socialmedia.socialmedia.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    User findUserById(Integer userId);

    List<User> findByUserNameContainingOrEmailContaining(String username, String Email);
}