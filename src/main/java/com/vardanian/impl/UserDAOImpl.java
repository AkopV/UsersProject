package com.vardanian.impl;

import com.vardanian.dao.DAO;
import com.vardanian.entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDAOImpl implements DAO<User>{

    @PersistenceContext
    private EntityManager entityManager;

    public void create(User user) {
        try {
            entityManager.persist(user);
        } catch (Exception e) {
            System.err.println("User not create: " + e);
        }
    }

    public void update(User user) {
        try {
            entityManager.persist(user);
        } catch (Exception e) {
            System.err.println("User not update: " + e);
        }
    }

    public List<User> list() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    public void remove(User user) {
        try {
            entityManager.remove(user);
        } catch (Exception e) {
            System.err.println("User not remove: " + e);
        }
    }

    public User findByLogin(String login) {
        return entityManager.find(User.class, login);
    }
}
