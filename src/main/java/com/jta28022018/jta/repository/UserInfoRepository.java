package com.jta28022018.jta.repository;

import com.jta28022018.jta.model.User;
import com.jta28022018.jta.model.UserInfo;

import java.util.List;

public interface UserInfoRepository {

    UserInfo save(UserInfo userInfo);
    UserInfo update(UserInfo userInfo);
    UserInfo findById(Integer userInfoId);
    UserInfo findByUser(User user);
    List<UserInfo> findAll();
}
