import com.vardanian.entities.Role;
import com.vardanian.entities.User;
import com.vardanian.impl.UserDAOImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

@ContextConfiguration(location = '/spring.xml')
public class UserDAOImplTest {

    @Autowired
    private UserDAOImpl userDAO;

    @Test
    public void testCreate() {
        int size = userDAO.list().size();
        userDAO.create(new User("login", "password", "firstName", "lastName", new Date(1986, 05, 26), new Role("admin")));
        assertTrue(size < userDAO.list().size());
    }

    @Test
    public void testList() {
        assertEquals(0, userDAO.list().size());

        List<User> users = Arrays.asList(
                new User("login1", "password1", "firstName1", "lastName1", new Date(1986, 05, 26), new Role("admin")),
                new User("login2", "password2", "firstName2", "lastName2", new Date(1986, 05, 27), new Role("user1")),
                new User("login3", "password3", "firstName3", "lastName3", new Date(1986, 05, 25), new Role("user2")));

        for (User user : users) {
            userDAO.create(user);
        }

        List<User> checkUser = userDAO.list();
        assertEquals(3, checkUser.size());
        for (User user : checkUser) {
            assertTrue(users.contains(checkUser));
        }
    }

    @Test
    public void testFindById(){
        User user = new User("login", "password", "firstName", "lastName", new Date(1986, 05, 26), new Role("admin"));
        userDAO.create(user);
        User checkUser = userDAO.findById(user.getId());
        assertEquals(user, checkUser);
    }

    @Test
    public void testRemove() {
        User user = new User("login", "password", "firstName", "lastName", new Date(1986, 05, 26), new Role("admin"));
        userDAO.create(user);
        assertEquals(user, userDAO.findById(user.getId()));
        userDAO.removed(user);
        assertNull(userDAO.findById(user.getId()));
    }
}
