package com.vardanian.service.impl;

import com.vardanian.entities.Role;
import com.vardanian.service.RoleService;
import com.vardanian.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.NoResultException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/springConfigurationTest.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class RoleDAOImplTest {

    Role role;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Before
    public void setUp() throws Exception{
        Role role = new Role("admin");
        roleService.create(role);
    }

    @Test
    public void testCreate() {
        Role checkRole = roleService.findByLogin("admin");
        assertEquals("admin", role.getName());
    }

    @Test
    public void testUpdate(){
        role.setName("admin");
        roleService.update(role);
        role = roleService.findByLogin("admin");
        assertEquals("admin", role.getName());
    }

    @Test (expected = NoResultException.class)
    public void testRemove() {
        roleService.remove(role);
        Role checkRole = roleService.findByLogin(role.getName());
        Assert.assertEquals(null, checkRole);
    }

    @Test
    public void testFindByLogin() {
        role = roleService.findByLogin("admin");
        assertEquals("admin", role.getName());
    }

    @Test
    public void testFindById() {
        role = roleService.findById(1L);
        assertEquals("1", role.getId().toString());
    }
}
