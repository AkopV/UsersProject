package com.vardanian.service.impl;

import com.vardanian.dao.UserDAO;
import com.vardanian.dao.impl.UserDAOImpl;
import com.vardanian.entities.User;
import com.vardanian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Rollback
    @Override
    public void create(User user) {
        userDAO.create(user);
    }

    @Override
    public void update(User user){
        userDAO.update(user);
    }

    @Override
    public User findById(Long id) {
        return userDAO.findById(id);
    }

    @Override
    public User findByLogin(String login) {
        return userDAO.findByLogin(login);
    }

    @Override
    public List<User> list() {
        return userDAO.list();
    }

    @Override
    public void remove(User user) {
        userDAO.remove(user);
    }

    public void setUserDAO(UserDAOImpl userDAO) {
    }
}
