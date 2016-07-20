package com.vardanian.service.impl;

import com.vardanian.dao.UserDAO;
import com.vardanian.entities.User;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAttribute;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

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
    public void create(User user) {
        try {
            entityManager.merge(user);

        } catch (Exception e) {
            System.err.println("User not create: " + e);
        }
    }
    @Override
    public void update(User user) {
        try {
            entityManager.merge(user);
        } catch (Exception e) {
            System.err.println("User not update: " + e);
        }
    }
    @Override
    public List<User> list() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void remove(User user) {
        try {
            entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
        } catch (Exception e) {
            System.err.println("User not remove: " + e);
        }
    }

    @Override
    public User findByLogin(String login) {
        String hsql = "SELECT user FROM User user WHERE user.login LIKE :login";
        return (User) entityManager.createQuery(hsql).setParameter("login", login).getSingleResult();
    }
}
