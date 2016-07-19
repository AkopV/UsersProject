package com.vardanian.dao.impl;

import com.vardanian.dao.RoleDAO;
import com.vardanian.dao.UserDAO;
import com.vardanian.entities.Role;
import com.vardanian.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/springConfigurationTest.xml")
public class UserDAOImplTest {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    private Calendar birthDay = GregorianCalendar.getInstance();

    @Test
    public void testCreate(){
        setUserBirthday(1986, Calendar.SEPTEMBER, 9);
        Date date = convertDate(birthDay);
        Role role = new Role("test");
        roleDAO.create(role);
        User user = new User(1L, "testuser", "password", "firstName", "lastName", date, role);
        userDAO.create(user);
        user = userDAO.findByLogin("testuser");
        assertEquals(user.getLogin(), "testuser");
    }

    private static final Date convertDate(Calendar date) {
        return new Date(date.getTimeInMillis());
    }

    private void setUserBirthday(int year, int month, int day) {
        birthDay.set(Calendar.YEAR, year);
        birthDay.set(Calendar.MONTH, month);
        birthDay.set(Calendar.DAY_OF_MONTH, day);
    }

    @Test
    public void testUpdate() {
        setUserBirthday(1986, Calendar.SEPTEMBER, 9);
        Date date = convertDate(birthDay);
        Role role = new Role("test");
        roleDAO.create(role);
        User user = new User(1L, "testuser", "password", "firstName", "lastName", date, role);
        userDAO.create(user);
        User user2 = new User(1L, "testuser2", "password", "firstName", "lastName", date, role);
        userDAO.update(user2);
        user2 = userDAO.findByLogin("testuser2");
        assertEquals(user2.getLogin(), "testuser2");
    }

    @Test
    public void testRemove() {
        setUserBirthday(1986, Calendar.SEPTEMBER, 9);
        Date date = convertDate(birthDay);
        Role role = new Role("test");
        roleDAO.create(role);
        User user = new User(1L, "testuser", "password", "firstName", "lastName", date, role);
        userDAO.create(user);
        User checkUser = userDAO.findByLogin("testuser");
        assertEquals(checkUser.getLogin(), "testuser");
        userDAO.remove(user);
        checkUser = userDAO.findByLogin("testuser");
        assertEquals(checkUser.getLogin(), null);
    }

    @Test
    public void testFindByLogin() {
        setUserBirthday(1986, Calendar.SEPTEMBER, 9);
        Date date = convertDate(birthDay);
        Role role = new Role("test");
        roleDAO.create(role);
        User user = new User(1L, "testuser", "password", "firstName", "lastName", date, role);
        userDAO.create(user);
        user = userDAO.findByLogin("testuser");
        assertEquals(user.getLogin(), "testuser");
    }

    @Test
    public void testFindById() {
        setUserBirthday(1986, Calendar.SEPTEMBER, 9);
        Date date = convertDate(birthDay);
        Role role = new Role("test");
        roleDAO.create(role);
        User user = new User(1L, "testuser", "password", "firstName", "lastName", date, role);
        userDAO.create(user);
        user = userDAO.findById(1L);
        assertEquals(user.getId().toString(), "1L");
    }
}
