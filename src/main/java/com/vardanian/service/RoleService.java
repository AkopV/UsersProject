package com.vardanian.service;

import com.vardanian.entities.Role;

import java.util.List;

public interface RoleService {

    void create(Role role);
    void update(Role role);
    Role findById(Long id);
    Role findByLogin(String login);
    List<Role> list();
    void remove(Role role);
}
