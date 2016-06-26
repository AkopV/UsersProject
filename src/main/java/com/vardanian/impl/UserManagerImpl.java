package com.vardanian.impl;

import com.vardanian.dao.DAO;
import com.vardanian.entities.User;
import com.vardanian.manager.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserManagerImpl implements Manager<User>{

    @Autowired
    private DAO<User> userDAO;

    @Override
    @Transactional
    public void insertUser(User user) {
        userDAO.create(user);
    }

    @Override
    public List<User> list() {
        return userDAO.list();
    }
}
