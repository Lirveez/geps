package mirea.geps.core.service;

import mirea.geps.core.dto.User;
import mirea.geps.core.service.db.UserDb;
import mirea.geps.core.service.db.UserInfoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthServiceTest {

    @Autowired
    private UserInfoRepository repository;
    @Autowired
    private AuthService authService;

    @MockBean
    private PasswordEncoder encoder;

    @Before
    public void setUp() throws Exception {
        repository.deleteAll();
        UserDb user = new UserDb();
        user.setLogin("login");
        user.setName("name");
        user.setSurname("surname");
        user.setDegree("degree");
        user.setPassword("encodedPassword");
        repository.save(user);
        when(encoder.matches("password", "encodedPassword")).thenReturn(true);
    }

    @Test
    public void shouldAuthUser() {
        User user = authService.authUser("login", "password");
        assertNotNull(user);
    }

    @Test
    public void shouldReturnNullIfNoUsernameFound() {
        User user = authService.authUser("404", "password");
        assertNull(user);
    }

    @Test
    public void shouldReturnNullIfIncorrectPassword() {
        User user = authService.authUser("login", "pass");
        assertNull(user);
    }

}