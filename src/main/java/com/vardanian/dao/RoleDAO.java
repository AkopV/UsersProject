package com.vardanian.dao;

import com.vardanian.entities.Role;

import java.util.List;

public interface RoleDAO {

    void create(Role role);

    void update(Role role);

    List<Role> list();

    Role findById(Long id);

    void remove(Role role);

    Role findByLogin(String login);
}
