package com.vardanian.impl;

import com.vardanian.dao.DAO;
import com.vardanian.entities.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class RoleDAOImpl implements DAO<Role> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Role role) {
        entityManager.persist(role);
    }

    @Override
    public List<Role> list() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Role> criteriaQuery = builder.createQuery(Role.class);
        Root<Role> roleRoot = criteriaQuery.from(Role.class);
        criteriaQuery.select(roleRoot);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Role findById(long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public void removed(Role role) {
        entityManager.remove(role);
    }
}
