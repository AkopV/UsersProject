package com.vardanian.service.impl;

import com.vardanian.entities.Role;
import com.vardanian.service.RoleService;
import org.hibernate.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/springConfigurationTest.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class RoleDAOImplTest {

    private Role role;

    @Autowired
    private RoleService roleService;

    @Before
    public void setUp() throws Exception {
        role = new Role(1L, "admin");

    }

    @Test
    public void testCreate() {
        roleService.create(role);
        Role checkRole = roleService.findByName("admin");
        assertEquals("admin", role.getName());
    }

    @Test
    public void testUpdate() {
        roleService.create(role);
        role.setName("user");
        roleService.update(role);
        role = roleService.findByName("user");
        assertEquals("user", role.getName());
    }

    @Test
    public void testList() {
        assertEquals(0, roleService.list().size());
        List<Role> roles = Arrays.asList(
                new Role(1L, "admin"),
                new Role(2L, "user"),
                new Role(3L, "user"));
        for (Role role : roles) {
            roleService.create(role);
        }
        List<Role> checkRoles = roleService.list();
        assertEquals(3, checkRoles.size());
    }

    @Test
    public void testRemove() {
        roleService.create(role);
        Role checkRole = roleService.findByName("admin");
        assertNotNull(checkRole);
        roleService.remove(checkRole);
        checkRole = roleService.findByName("admin");
        assertEquals(null, checkRole);
    }

    @Test
    public void testFindByName() {
        roleService.create(role);
        Role checkRole = roleService.findByName("admin");
        assertEquals("admin", checkRole.getName());
    }

    @Test
    public void testFindById() {
        roleService.create(role);
        Role checkRole = roleService.findById(role.getId());
        assertNotNull(checkRole);
    }
}
