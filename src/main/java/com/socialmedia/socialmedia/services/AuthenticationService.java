package com.socialmedia.socialmedia.services;

import com.socialmedia.socialmedia.dtos.LoginUserDto;
import com.socialmedia.socialmedia.dtos.RegisterUserDto;
import com.socialmedia.socialmedia.entities.User;
import com.socialmedia.socialmedia.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public User signup(RegisterUserDto registerUserDto) {
        User user = new User()
                .setUserName(registerUserDto.getUserName())
                .setEmail(registerUserDto.getEmail())
                .setPassword(passwordEncoder.encode(registerUserDto.getPassword()));

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail()).orElseThrow();
    }

    public List<User> allUsers() {
        return new ArrayList<>(userRepository.findAll());
    }
}
