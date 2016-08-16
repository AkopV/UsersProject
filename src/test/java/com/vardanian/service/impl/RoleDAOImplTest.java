package com.vardanian.service.impl;

import com.vardanian.entities.Role;
import com.vardanian.service.RoleService;
import com.vardanian.service.UserService;
import com.vardanian.utils.CloseableSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/springConfigurationTest.xml")
public class RoleDAOImplTest {

    private Role role;

    @Autowired
    private RoleService roleService;

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
        role = new Role(1L, "admin");
    }

    @After
    public void tearDown() throws Exception {
        try (CloseableSession session = new CloseableSession(
                sessionFactory.openSession())){
            session.getSession().createQuery("delete from Role").executeUpdate();
        } catch (Exception e) {
            System.err.println("Table 'Role' weren't removed");
        }
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
        assertTrue(roleService.list().isEmpty());
        List<Role> roles = Arrays.asList(
                new Role(1L, "admin"),
                new Role(2L, "user"),
                new Role(3L, "user"));
        createRoles(roles);
        List<Role> checkRoles = roleService.list();
        assertEquals(3, checkRoles.size());
    }

    private void createRoles(List<Role> roles) {
        for (Role role : roles) {
            roleService.create(role);
        }
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
