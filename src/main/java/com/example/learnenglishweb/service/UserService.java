package com.example.learnenglishweb.service;

import com.example.learnenglishweb.Entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(int id);
    User findByEmail(String email);
    void save(User user);
    void update(User user);
    void deleteById(int id);
}
