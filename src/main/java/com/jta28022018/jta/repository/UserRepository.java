package com.jta28022018.jta.repository;

import com.jta28022018.jta.model.User;

import java.util.List;

public interface UserRepository {

        User save(User user);
        User update(User user);
        User findById(Integer userId);
        List<User> findAll();
        User findByUsername(String username);
}
