package com.vardanian.dao.impl;

import com.vardanian.dao.RoleDAO;
import com.vardanian.entities.Role;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext(unitName = "Tutorial")
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void create(Role role) {
        try {
            entityManager.merge(role);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Role role) {
        entityManager.merge(role);
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
    public Role findById(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public void remove(Role role) {
        entityManager.remove(role);
    }

    @Override
    public Role findByLogin(String login) {
        return entityManager.find(Role.class, login);
    }
}
