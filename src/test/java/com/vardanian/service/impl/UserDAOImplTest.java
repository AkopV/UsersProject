package com.vardanian.service.impl;

import com.vardanian.entities.Role;
import com.vardanian.entities.User;
import com.vardanian.service.RoleService;
import com.vardanian.service.UserService;
import com.vardanian.utils.TestUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/springConfigurationTest.xml")
public class UserDAOImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    User user;
    Role role;
    Date date;

    @Before
    public void setUp() throws Exception {
        TestUtils.setUserBirthday(1986, Calendar.SEPTEMBER, 9);
        date = TestUtils.convertDate(TestUtils.birthDay);
        role = new Role("test");
        user = new User(1L, "testuser", "password", "firstName", "lastName", date, role);
    }

    @Test
    public void testCreate() {
        userService.create(user);
        user = userService.findByLogin("testuser");
        assertEquals(user.getLogin(), "testuser");
    }

    @Test
    public void testUpdate() {
        userService.create(user);
        user.setLogin("testuser2");
        userService.update(user);
        user = userService.findByLogin("testuser2");
        assertEquals(user.getLogin(), "testuser2");
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testRemove() {
        userService.create(user);
        if(user != null) {
            userService.remove(user);
        }
        Assert.assertEquals(user.getLogin(), null);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testFindByLogin() {
        userService.create(user);
        user = userService.findByLogin("testuser");
        assertEquals(user.getLogin(), "testuser");
    }

    @Test
    public void testFindById() {
        userService.create(user);
        user = userService.findById(1L);
        assertEquals(user.getId().toString(), "1");
    }
}
