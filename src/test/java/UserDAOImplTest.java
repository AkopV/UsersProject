import com.vardanian.entities.Role;
import com.vardanian.entities.User;
import com.vardanian.impl.UserDAOImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springConfigurationTest.xml")
public class UserDAOImplTest {

    @Autowired
    private UserDAOImpl userDAO;

    @BeforeTest
    public void beforeRemove(){
        try {
            for (User user : userDAO.list()) {
                userDAO.remove(user);
            }
        } catch (Exception e) {
            System.err.println("Error, list empty: " + e);
        }
    }

    @Test
    @Transactional
    public void testCreate() {
        userDAO.create(new User("login", "password", "firstName", "lastName", new Date(1986, 05, 26), new Role("admin")));
        assertEquals(userDAO, true);
    }

    @Test
    @Transactional
    public void testFindById(){
        User user = new User("login", "password", "firstName", "lastName", new Date(1986, 05, 26), new Role("admin"));
        userDAO.create(user);
        User checkUser = userDAO.findById(user.getId());
        assertEquals(user, checkUser);
    }

    @Test
    @Transactional
    public void testFindByLogin() {
        User user = new User("login", "password", "firstName", "lastName", new Date(1986, 05, 26), new Role("admin"));
        userDAO.create(user);
        User checkUser = userDAO.findByLogin(user.getLogin());
        assertEquals(user, checkUser);
    }

    @Test
    @Transactional
    public void testRemove() {
        User user = new User("login", "password", "firstName", "lastName", new Date(1986, 05, 26), new Role("admin"));
        userDAO.create(user);
        User checkUser = userDAO.findById(user.getId());
        assertEquals(user, checkUser);;
        userDAO.remove(user);
        assertNull(userDAO.findById(user.getId()));
    }
}
