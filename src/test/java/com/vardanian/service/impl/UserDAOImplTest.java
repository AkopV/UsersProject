package com.vardanian.service.impl;

import com.vardanian.entities.Role;
import com.vardanian.entities.User;
import com.vardanian.service.UserService;
import com.vardanian.utilsjava.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/springConfigurationTest.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserDAOImplTest {

    User user;

    @Autowired
    private UserService userService;

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
    public void testRemove() {
        User checkUser = userService.findByLogin("testuser");
        assertNotNull(checkUser);
        userService.remove(checkUser);
        checkUser = userService.findByLogin("test");
        assertNull(checkUser);
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
        user = userService.findById(1L);
        assertEquals("1", user.getId().toString());
    }
}
