package com.vardanian.service.impl;

import com.vardanian.dao.DAO;
import com.vardanian.entities.User;
import com.vardanian.service.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class UserServiceImpl implements Service<User> {

    @Autowired
    private DAO<User> userDAO;

    public void create(User user) {
        userDAO.create(user);
    }

    public User getId(long id) {
        return userDAO.findById(id);
    }

    public User getByLogin(String login) {
        return userDAO.findByLogin(login);
    }

    public List<User> getAll() {
        return userDAO.list();
    }

    public void remove(User user) {
        userDAO.remove(user);
    }
}
