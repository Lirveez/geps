package mirea.geps.core.service.db;

import mirea.geps.core.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserDb, Long> {
    Optional<UserDb> findByLogin(String login);

    default User findUserByLogin(String login) {
        return findByLogin(login).map(user -> new User(user.getLogin(),
                user.getPassword(),
                user.getName(),
                user.getSurname(),
                user.getPatronymic(),
                user.getDegree())).orElse(null);
    }
}
