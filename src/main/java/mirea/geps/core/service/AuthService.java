package mirea.geps.core.service;

import lombok.AllArgsConstructor;
import mirea.geps.core.dto.User;
import mirea.geps.core.service.db.UserInfoRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserInfoRepository repository;
    private final PasswordEncoder encoder;

    public User authUser(String login, String password) {
        User user = repository.findUserByLogin(login);
        if (user != null && encoder.matches(password,user.getPassword()))
            return user;
        else
            return null;
    }
}
