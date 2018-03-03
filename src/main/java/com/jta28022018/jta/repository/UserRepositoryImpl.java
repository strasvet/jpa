package com.jta28022018.jta.repository;

import com.jta28022018.jta.model.User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public User update(User user) {
        entityManager.merge(user);
        return user;
    }

    @Override
    public User findById(Integer userId) {
        return entityManager.find(User.class, userId);

    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("from User").getResultList();
    }

    @Override
    public User findByUsername(String username) {
        List users = entityManager.createQuery("from User u where u.username = :usernameP")
                .setParameter("usernameP",username).getResultList();
        if (users.isEmpty()){
            return null;
        }
        return (User) users.get(0);
    }
}
