package com.shoplyft.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shoplyft.backend.model.User;
import com.shoplyft.backend.repository.UserJPARepository;

@Service
public class UserService {

    private final UserJPARepository repository;

    public UserService(UserJPARepository repository) {
        this.repository = repository;
    }

    public List<User> findAllUsers() {
        return repository.findAll();
    }

    public Optional<User> findUserByUserId(Long userId) {
        return repository.findById(userId);
    }

    public void deleteUserByUserId(Long userId) {
        repository.deleteById(userId);
    }
}
