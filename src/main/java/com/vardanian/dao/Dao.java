package com.vardanian.dao;

import java.util.List;

/**
 * interface which is responsible for CRUD (https://ru.wikipedia.org/wiki/CRUD)
 * @param <T> what kind of entity is used*/
public interface DAO<T> {

    /**
     * It creates an entity in the database
     * @param entity parameters */
    void create(T entity);

    /**
     * List of entities
     * @return*/
    List<T> list();

    /**
     * It removes this entity
     * @param entity parameters that can be removed
     * @return removed or not*/
    boolean removed(T entity);
}
