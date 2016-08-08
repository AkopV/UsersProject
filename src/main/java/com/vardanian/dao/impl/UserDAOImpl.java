package com.vardanian.dao.impl;

import com.vardanian.dao.UserDAO;
import com.vardanian.entities.User;
import com.vardanian.utils.Utils;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
            User userToBeRemoved = entityManager.getReference(User.class, user.getId());
            entityManager.remove(userToBeRemoved);
        } catch (Exception e) {
            System.err.println("User not remove: " + e);
        }
    }

    @Override
    public User findByLogin(String login) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.login LIKE :login")
                .setParameter("login", login);
        return (User) Utils.getSingleResult(query);
    }
}
