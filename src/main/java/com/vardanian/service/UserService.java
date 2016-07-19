package com.vardanian.service;

import com.vardanian.entities.User;

import java.util.List;

/**
 * interface which is responsible for business login for get parameters*/
public interface UserService {

    void create(User user);
    void update(User user);
    User findById(Long id);
    User findByLogin(String login);
    List<User> list();
    void remove(User user);
}

