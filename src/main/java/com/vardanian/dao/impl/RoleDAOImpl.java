package com.vardanian.dao.impl;

import com.vardanian.dao.RoleDAO;
import com.vardanian.entities.Role;
import com.vardanian.utils.Utils;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Role role) {
        try {
        entityManager.merge(role);
        } catch (Exception e) {
            System.err.println("Role not update: " + e);
        }
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
        try {
            Role roleToBeRemoved = entityManager.getReference(Role.class, role.getId());
            entityManager.remove(roleToBeRemoved);
        } catch (Exception e) {
            System.err.println("Role not remove: " + e);
        }
    }


    @Override
    public Role findByLogin(String login) {
        Query query = entityManager.createQuery("SELECT role FROM Role role WHERE role.name LIKE :name")
                .setParameter("name", login);
        return (Role) Utils.getSingleResult(query);

    }
}