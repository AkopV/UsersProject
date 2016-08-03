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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/springConfigurationTest.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class RoleDAOImplTest {

    private Role role;

    @Autowired
    private RoleService roleService;

    @Before
    public void setUp() throws Exception {
        role = new Role("admin");
        roleService.create(role);
    }

    @Test
    public void testCreate() {
        roleService.create(role);
        Role checkRole = roleService.findByLogin("admin");
        assertEquals("admin", role.getName());
    }

    @Test
    public void testUpdate() {
        roleService.create(role);
        role.setName("admin");
        roleService.update(role);
        role = roleService.findByLogin("admin");
        assertEquals("admin", role.getName());
    }

    @Test(expected = AssertionError.class)
    public void testRemove() {
        Role checkRole = roleService.findByLogin("admin");
        assertNotNull(checkRole);
        roleService.remove(checkRole);
        checkRole = roleService.findByLogin("admin");
        assertNull(checkRole);
    }

    @Test
    public void testFindByLogin() {
        roleService.create(role);
        role = roleService.findByLogin("admin");
        assertEquals("admin", role.getName());
    }

    @Test
    public void testFindById() {
        roleService.create(role);
        role = roleService.findById(1L);
        assertEquals("1", role.getId().toString());
    }
}
