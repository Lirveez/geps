package mirea.geps.core.service.db;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserInfoRepositoryTest {

    @Autowired
    private UserInfoRepository repository;

    @Before
    public void setUp() throws Exception {
        repository.deleteAll();
        UserDb user = new UserDb();
        user.setLogin("login");
        user.setName("name");
        user.setPassword("pwd");
        user.setSurname("surname");

        repository.save(user);
    }

    @Test
    public void shouldFindByLogin() {
        Optional<UserDb> user = repository.findByLogin("login");

        assertTrue(user.isPresent());
        assertEquals("name", user.get().getName());
        assertEquals("pwd", user.get().getPassword());
        assertEquals("surname", user.get().getSurname());
    }


}