package com.socialmedia.socialmedia.controllers;

import com.socialmedia.socialmedia.entities.User;
import com.socialmedia.socialmedia.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> allUsers() {
        List <User> users = userService.allUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/userDetail")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable Integer  userId) {
        return userService.findUserById(userId);
    }

    @GetMapping("/users/search")
    public List<User> searchUser(@RequestParam("query") String query) {
        return userService.searchUser(query);
    }

    @PutMapping("/users/{userId}")
    public User updateUser(@PathVariable Integer userId, @RequestBody User updatedUser) {
        updatedUser.setId(userId);
        return userService.updateUser(updatedUser);
    }
    @PutMapping("/users/follow/{userId1}/{userId2}")
    public User followUserHandler(@PathVariable Integer userId1, @PathVariable Integer userId2) {
        return userService.followUser(userId1, userId2);
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable("userId") Integer userId) {
        try {
            userService.deleteUser(userId);
            return "User deleted successfully";
        } catch (EntityNotFoundException ex) {
            return "User not found with the id: " + userId;
        } catch (Exception ex) {
            return "An error occurred while deleting the user";
        }
    }
}
