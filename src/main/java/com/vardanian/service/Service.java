package com.vardanian.service;

import java.util.List;

/**
 * interface which is responsible for business login for get parameters
 * @param <T> what kind of entity is used*/
public interface Service<T> {

    void create(T entity);
    T getId(long id);
    T getByLogin(String login);
    List<T> getAll();
    void remove(T entity);
}

