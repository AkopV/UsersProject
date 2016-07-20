package com.vardanian.service.impl;

import com.vardanian.dao.UserDAO;
import com.vardanian.entities.User;
import com.vardanian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

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
    @Transactional(rollbackFor = Exception.class)
    public void remove(User user) {
        userDAO.remove(user);
    }
}
