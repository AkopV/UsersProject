package com.vardanian.impl;

import com.vardanian.dao.DAO;
import com.vardanian.entities.Role;
import com.vardanian.manager.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleManagerImpl implements Manager<Role>{

    @Autowired
    private DAO<Role> roleDAO;

    @Override
    public void insertUser(Role role) {
        roleDAO.create(role);
    }

    @Override
    public List<Role> list() {
        return roleDAO.list();
    }
}
