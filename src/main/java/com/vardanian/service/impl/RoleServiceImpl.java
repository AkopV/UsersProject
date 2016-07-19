package com.vardanian.service.impl;

import com.vardanian.dao.RoleDAO;
import com.vardanian.entities.Role;
import com.vardanian.service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    private RoleDAO roleDAO;

    @Override
    public void create(Role role) {
        roleDAO.create(role);
    }

    @Override
    public void update(Role role) {
        roleDAO.update(role);
    }

    @Override
    public Role findById(Long id) {

        return roleDAO.findById(id);
    }

    @Override
    public Role findByLogin(String login) {
        return roleDAO.findByLogin(login);
    }

    @Override
    public List<Role> list() {
        return roleDAO.list();
    }

    @Override
    public void remove(Role role) {
        roleDAO.remove(role);
    }
}
