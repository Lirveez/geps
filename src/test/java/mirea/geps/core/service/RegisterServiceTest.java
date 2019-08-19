package mirea.geps.core.service;

import mirea.geps.core.dto.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegisterServiceTest {

    @Autowired
    private RegisterService registerService;

    @Test
    public void shouldRegisterUser() {

        User user= registerService.registerUser("login", "pwd", "name", "surname", "patronymic", "degree");

        assertEquals("name", user.getName());
        assertEquals("surname", user.getSurname());
        assertEquals("patronymic", user.getPatronymic());
        assertEquals("degree", user.getDegree());
    }

}