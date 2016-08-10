package com.vardanian.service.impl;

import com.vardanian.entities.Role;
import com.vardanian.entities.User;
import com.vardanian.service.UserService;
import com.vardanian.utils.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/springConfigurationTest.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserDAOImplTest {

    private User user;

    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        TestUtils.setUserBirthday(1986, Calendar.SEPTEMBER, 9);
        Date date = TestUtils.convertDate(TestUtils.birthDay);
        Role role = new Role(1L, "test");
        user = new User(1L, "testuser", "password", "firstName", "lastName", date, role);
    }

    @Test
    public void testCreate() {
        userService.create(user);
        User user = userService.findByLogin("testuser");
        assertEquals("testuser", user.getLogin());
    }

    @Test
    public void testUpdate() {
        userService.create(user);
        user.setLogin("testuser2");
        userService.update(user);
        user = userService.findByLogin("testuser2");
        assertEquals("testuser2", user.getLogin());
    }

    @Test
    public void testList() {
        List<User> users;
        assertEquals(0, userService.list().size());
        users = Arrays.asList(
                new User(1L, "login", "password", "firstName", "lastName", new Date(1990, 5, 9), new Role(1L, "admin")),
                new User(2L, "login2", "password2", "firstName2", "lastName2", new Date(1992, 8, 19), new Role(2L, "user")),
                new User(3L, "login3", "password23", "firstName3", "lastName2", new Date(1992, 8, 19), new Role(3L, "user")));
        for (User user : users) {
            userService.create(user);
        }
        List<User> checkUsers = userService.list();
        assertEquals(3, checkUsers.size());
    }

    @Test
    public void testRemove() {
        userService.create(user);
        User checkUser = userService.findByLogin("testuser");
        assertNotNull(checkUser);
        userService.remove(checkUser);
        checkUser = userService.findByLogin(user.getLogin());
        assertEquals(null, checkUser);
    }

    @Test
    public void testFindByLogin() {
        userService.create(user);
        user = userService.findByLogin("testuser");
        assertEquals("testuser", user.getLogin());
    }

    @Test
    public void testFindById() {
        userService.create(user);
        User checkUser = userService.findById(user.getId());
        assertNotNull(checkUser);
    }
}
