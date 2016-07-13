import com.vardanian.entities.Role;
import com.vardanian.entities.User;
import com.vardanian.impl.UserDAOImpl;
import com.vardanian.service.impl.UserServiceDAOImpl;
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

    @Autowired
    private UserServiceDAOImpl userServiceDAO;

    @Test
    public void testCreate() {
        userServiceDAO.create(new User("login", "password", "firstName", "lastName", new Date(1986, 05, 26), new Role("admin")));
        assertEquals(userServiceDAO, true);
    }

    @Test
    public void testFindById(){
        User user = new User("login", "password", "firstName", "lastName", new Date(1986, 05, 26), new Role("admin"));
        userServiceDAO.create(user);
        User checkUser = userServiceDAO.getById(user.getId());
        assertEquals(user, checkUser);
    }

    @Test
    public void testFindByLogin() {
        User user = new User("login", "password", "firstName", "lastName", new Date(1986, 05, 26), new Role("admin"));
        userServiceDAO.create(user);
        assertEquals(user, userServiceDAO.getByLogin(user.getLogin()));
    }

    @Test
    public void testRemove() {
        User user = new User("login", "password", "firstName", "lastName", new Date(1986, 05, 26), new Role("admin"));
        userServiceDAO.create(user);
        User checkUser = userServiceDAO.getById(user.getId());
        assertEquals(user, checkUser);;
        userServiceDAO.remove(user);
        checkUser = userServiceDAO.getById(user.getId());
        assertNull(checkUser);
    }
}
