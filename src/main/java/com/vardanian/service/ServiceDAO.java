package com.vardanian.service;

import java.util.List;

/**
 * interface which is responsible for business login for get parameters
 * @param <T> what kind of entity is used*/
public interface ServiceDAO<T> {

    void create(T entity);
    void update(T entity);
    T getById(Long id);
    T getByLogin(String login);
    List<T> getAll();
    void remove(T entity);
}

