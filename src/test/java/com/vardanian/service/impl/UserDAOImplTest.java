package com.vardanian.service.impl;

import com.vardanian.entities.Role;
import com.vardanian.entities.User;
import com.vardanian.service.UserService;
import com.vardanian.utils.CloseableSession;
import com.vardanian.utils.TestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/springConfigurationTest.xml")
public class UserDAOImplTest {

    private User user;

    @Autowired
    private UserService userService;

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Before
    public void setUp() throws Exception {
        TestUtils.setUserBirthday(1986, Calendar.SEPTEMBER, 9);
        Date date = TestUtils.convertDate(TestUtils.birthDay);
        Role role = new Role(1L, "test");
        user = new User(1L, "testuser", "password", "firstName", "lastName", date, role);
    }

    @After
    public void tearDown() throws Exception {
        try (CloseableSession session = new CloseableSession(
                sessionFactory.openSession())){
            session.getSession().createSQLQuery("TRUNCATE TABLE User").executeUpdate();
        } catch(Exception e) {
            System.err.println("Table 'User' wasn't removed");
        }
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testCreate() {
        userService.create(user);
        User user = userService.findByLogin("testuser");
        assertEquals("testuser", user.getLogin());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testUpdate() {
        userService.create(user);
        user.setLogin("testuser2");
        userService.update(user);
        user = userService.findByLogin("testuser2");
        assertEquals("testuser2", user.getLogin());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testList() {
        assertTrue(userService.list().isEmpty());
        List<User> users = Arrays.asList(
                new User(2L, "login", "password", "firstName", "lastName", new Date(1990, 5, 9), new Role(3L, "admin")),
                new User(3L, "login2", "password2", "firstName2", "lastName2", new Date(1992, 8, 19), new Role(3L, "user")),
                new User(4L, "login3", "password23", "firstName3", "lastName2", new Date(1992, 8, 19), new Role(4L, "user")));
        createUsers(users);
        List<User> checkUsers = userService.list();
        assertEquals(3, checkUsers.size());
    }

    private void createUsers(List<User> users) {
        for (User user : users) {
            userService.create(user);
        }
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testRemove() {
        userService.create(user);
        User checkUser = userService.findByLogin("testuser");
        assertNotNull(checkUser);
        userService.remove(checkUser);
        checkUser = userService.findByLogin(user.getLogin());
        assertEquals(null, checkUser);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testFindByLogin() {
        userService.create(user);
        user = userService.findByLogin("testuser");
        assertEquals("testuser", user.getLogin());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testFindById() {
        userService.create(user);
        User checkUser = userService.findById(user.getId());
        assertNotNull(checkUser);
    }
}
