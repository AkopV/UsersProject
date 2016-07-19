package com.vardanian.dao;

import com.vardanian.entities.User;

import java.util.List;

/**
 * interface which is responsible for CRUD (https://ru.wikipedia.org/wiki/CRUD)*/
public interface UserDAO {

    void create(User user);

    void update(User user);

    List<User> list();

    User findById(Long id);

    void remove(User user);

    User findByLogin(String login);
}
