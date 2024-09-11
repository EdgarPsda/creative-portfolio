package com.psda.creative_portfolio.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.psda.creative_portfolio.model.User;
import com.psda.creative_portfolio.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves a list of all users from the database.
     * 
     * @return a list of User objects
     */
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves user data from the database.
     * 
     * @param id
     * @return user object created.
     */
    public Optional<User> getUserById(Long id) {
        if (id == null) {
            throw new NullPointerException("User ID cannot be null");
        }
        return Optional.of(userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with ID: " + id)));
    }

}
