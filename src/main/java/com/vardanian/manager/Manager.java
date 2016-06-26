package com.vardanian.manager;

import java.util.List;

public interface Manager<T> {

    void insertUser(T entity);

    List<T> list();
}
