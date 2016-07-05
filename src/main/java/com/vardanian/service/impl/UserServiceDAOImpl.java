package com.vardanian.service.impl;

import com.vardanian.dao.DAO;
import com.vardanian.entities.User;
import com.vardanian.service.ServiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class UserServiceDAOImpl implements ServiceDAO<User> {

    @Autowired
    private DAO<User> userDAO;

    public void create(User user) {
        userDAO.create(user);
    }

    public void update(User user){
        userDAO.update(user);
    }

    public User getById(Long id) {
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
