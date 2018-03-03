package com.jta28022018.jta.service;

import com.jta28022018.jta.model.ErrorToSave;
import com.jta28022018.jta.model.UserRequest;
import com.jta28022018.jta.model.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public interface UserService {
    UserResponse create(UserRequest userRequest);
    UserResponse update(UserRequest userRequest,Integer userId);
    List<UserResponse> getAll();
    UserResponse getByUsername(String username);
    ErrorToSave save(ErrorToSave errorToSave);
}
