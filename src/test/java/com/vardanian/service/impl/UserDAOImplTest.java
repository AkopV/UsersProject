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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.NoResultException;
import java.sql.Date;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/springConfigurationTest.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserDAOImplTest {

    User user;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Before
    public void setUp() throws Exception {
        TestUtils.setUserBirthday(1986, Calendar.SEPTEMBER, 9);
        Date date = TestUtils.convertDate(TestUtils.birthDay);
        Role role = new Role("test");
        user = new User(1L, "testuser", "password", "firstName", "lastName", date, role);
        userService.create(user);
    }

    @Test
    public void testCreate() {
        userService.create(user);
        User user = userService.findByLogin("testuser");
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

    @Test (expected = NoResultException.class)
    public void testRemove() {
        userService.create(user);
        userService.remove(user);
        User check = userService.findByLogin(user.getLogin());
        Assert.assertEquals(null, check);
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
