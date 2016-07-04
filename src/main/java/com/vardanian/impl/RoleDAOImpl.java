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

    public void create(Role role) {
        try {
            entityManager.persist(role);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Role> list() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Role> criteriaQuery = builder.createQuery(Role.class);
        Root<Role> roleRoot = criteriaQuery.from(Role.class);
        criteriaQuery.select(roleRoot);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public Role findById(long id) {
        return entityManager.find(Role.class, id);
    }

    public void remove(Role role) {
        entityManager.remove(role);
    }

    public Role findByLogin(String login) {
        return entityManager.find(Role.class, login);
    }
}
