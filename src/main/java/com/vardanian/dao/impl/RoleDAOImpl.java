package com.vardanian.dao.impl;

import com.vardanian.dao.RoleDAO;
import com.vardanian.entities.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;

public class RoleDAOImpl implements RoleDAO {

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
    public void create(Role role) {
        try {
//            entityManager.persist(role);
//            entityManager.flush();
            currentSession().save(role);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Role role) {
        try {
//        entityManager.merge(role);
            currentSession().saveOrUpdate(role);
        } catch (Exception e) {
            System.err.println("Role not update: " + e);
        }
    }

    @Override
    public List<Role> list() {
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Role> criteriaQuery = builder.createQuery(Role.class);
//        Root<Role> roleRoot = criteriaQuery.from(Role.class);
//        criteriaQuery.select(roleRoot);
//        return entityManager.createQuery(criteriaQuery).getResultList();
        return currentSession().createCriteria(Role.class).list();
    }

    @Override
    public Role findById(Long id) {
//        return entityManager.find(Role.class, id);
        List<Role> roles = new ArrayList<Role>();
        roles = sessionFactory.getCurrentSession().createQuery("FROM Role role WHERE role.id=?").setParameter(0, id).list();
        if (roles.size() > 0) {
            return roles.get(0);
        } else  {
            return null;
        }
    }

    @Override
    public void remove(Role role) {
//        entityManager.remove(role);
        try {
            currentSession().delete(role);
        } catch (Exception e) {
            System.err.println("Role not remove: " + e);
        }
    }

    @Override
    public Role findByLogin(String login) {
        List<Role> roles = new ArrayList<Role>();
        roles = sessionFactory.getCurrentSession().createQuery("FROM Role role WHERE role.name=?").setParameter(0, login).list();
        if (roles.size() > 0) {
            return roles.get(0);
        } else {
            return null;
        }
//        String hsql = "SELECT role FROM Role role WHERE role.name LIKE :login";
//        return (Role) entityManager.createQuery(hsql).setParameter("login", login).getSingleResult();
    }
}
