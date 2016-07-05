package com.vardanian.dao;

import java.util.List;

/**
 * interface which is responsible for CRUD (https://ru.wikipedia.org/wiki/CRUD)
 * @param <T> what kind of entity is used*/
public interface DAO<T> {

    void create(T entity);

    void update(T entity);

    List<T> list();

    T findById(Long id);

    void remove(T entity);

    T findByLogin(String login);
}
