package com.vardanian.dao.impl;

import com.vardanian.dao.UserDAO;
import com.vardanian.entities.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAttribute;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

//    @PersistenceContext(unitName = "Tutorial")
//    private EntityManager entityManager;
//
//    public EntityManager getEntityManager() {
//        return entityManager;
//    }
//
//    public void setEntityManager(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void create(User user) {
        try {
//            entityManager.merge(user);
            currentSession().save(user);
        } catch (Exception e) {
            System.err.println("User not create: " + e);
        }
    }
    @Override
    public void update(User user) {
        try {
//            entityManager.merge(user);
            currentSession().saveOrUpdate(user);
        } catch (Exception e) {
            System.err.println("User not update: " + e);
        }
    }
    @Override
    public List<User> list() {
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
//        Root<User> root = criteriaQuery.from(User.class);
//        criteriaQuery.select(root);
//        return entityManager.createQuery(criteriaQuery).getResultList();
        return currentSession().createCriteria(User.class).list();
    }

    @Override
    public User findById(Long id) {
        List<User> users = new ArrayList<User>();
        users = sessionFactory.getCurrentSession().createQuery("FROM User user WHERE user.id=?").setParameter(0, id).list();
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }

//        return entityManager.find(User.class, id);
    }

    @Override
    public void remove(User user) {
        try {
            currentSession().delete(user);
        } catch (Exception e) {
            System.err.println("User not remove: " + e);
        }

//        try {
//            entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
//        } catch (Exception e) {
//            System.err.println("User not remove: " + e);
//        } finally {
//            entityManager.close();

    }

    @Override
    public User findByLogin(String login) {
        List<User> users = new ArrayList<User>();
        users = sessionFactory.getCurrentSession().createQuery("FROM User user WHERE user.login=?").setParameter(0, login).list();
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
//        String hsql = "SELECT user FROM User user WHERE user.login LIKE :login";
//        return (User) entityManager.createQuery(hsql).setParameter("login", login).getSingleResult();
//        Session session = getSessionFactory().openSession();
    }
}
