package com.jta28022018.jta.repository;

import com.jta28022018.jta.model.User;
import com.jta28022018.jta.model.UserInfo;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UserInfoRepositoryImpl implements UserInfoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserInfo save(UserInfo userInfo) {
        entityManager.persist(userInfo);
        return userInfo;
    }

    @Override
    public UserInfo update(UserInfo userInfo) {
        entityManager.merge(userInfo);
        return userInfo;
    }

    @Override
    public UserInfo findById(Integer userInfoId) {
       return entityManager.find(UserInfo.class, userInfoId);
    }

    @Override
    public UserInfo findByUser(User user) {
        String jpql = "from UserInfo ui where ui.user.id = :userId";
        List users = entityManager
                .createQuery(jpql)
                .setParameter("userId", user.getId())
                .getResultList();
        if(users.isEmpty()){
            return null;
        }
        return (UserInfo) users.get(0);
    }

    @Override
    public List<UserInfo> findAll() {
        return entityManager.createQuery("from UserInfo").getResultList();
    }
}
