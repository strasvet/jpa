package com.jta28022018.jta.service;

import com.jta28022018.jta.model.*;
import com.jta28022018.jta.repository.UserInfoRepository;
import com.jta28022018.jta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
//@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    //@Transactional(rollbackFor = Exception.class) //ne vipolnayT transaction
    //@Transactional // po umolchanue ne sohranit
    //@Transactional(noRollbackFor = RuntimeException.class,propagation = Propagation.REQUIRED)//vipolnyat v lubom sluchae, i vseh ego naslednikov
    //@Transactional(rollbackFor = RuntimeException.class)
    @Transactional
    public UserResponse create(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        userRepository.save(user);

        //if(0==0) throw new Exception("Some exception);
        //if(0==0)throw new RuntimeException("Unchecked exception");
        UserInfo userInfo = new UserInfo();
        userInfo.setUser(user);
        userInfo.setFirstName(userRequest.getFirstName());
        userInfo.setLastName(userRequest.getLastName());
        userInfoRepository.save(userInfo);

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(userInfo.getFirstName())
                .lastName(userInfo.getLastName())
                .build();
    }

    @Override
    @Transactional
    public UserResponse update(UserRequest userRequest, Integer userId) {
        User user = userRepository.findById(userId);
        user.setUsername(userRequest.getUsername());
        userRepository.update(user);

        UserInfo userInfo = userInfoRepository.findByUser(user);
        userInfo.setFirstName(userRequest.getFirstName());
        userInfo.setLastName(userRequest.getLastName());
        userInfoRepository.save(userInfo);

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(userInfo.getFirstName())
                .lastName(userInfo.getLastName())
                .build();
    }

    @Override
    @Transactional
    public List<UserResponse> getAll() {

        //List<User> users = userRepository.findAll();
        List<UserInfo> userInfos = userInfoRepository.findAll();
        //return userInfos.stream().map(userInfo -> UserResponse.builder().id(userInfo.getUser()).build()).collect(Collectors.toList());
        List<UserResponse> list = new ArrayList<>();
        for(UserInfo userInfo:userInfos){
            list.add(UserResponse.builder()
                    .id(userInfo.getUser().getId())
                    .username(userInfo.getUser().getUsername())
                    .firstName(userInfo.getFirstName())
                    .lastName(userInfo.getLastName())
                    .build());
        }
        return list;
    }

    @Override
    @Transactional
    public UserResponse getByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new NoSuchElementException("Not FOUND username: "+ username +" in DATABASE!");
            // throw new RuntimeException("Not Found user in database!");
        }
        UserInfo userInfo = userInfoRepository.findByUser(user);
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(userInfo.getFirstName())
                .lastName(userInfo.getLastName())
                .build();
    }

    @Override
    @Transactional
    public ErrorToSave save(ErrorToSave errorToSave) {
        entityManager.persist(errorToSave);
        return errorToSave;
    }
}
