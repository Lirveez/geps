package mirea.geps.core.service;

import lombok.AllArgsConstructor;
import mirea.geps.core.dto.User;
import mirea.geps.core.service.converters.UserConverter;
import mirea.geps.core.service.db.UserInfoRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RegisterService {

    private final PasswordEncoder encoder;
    private final UserInfoRepository repository;
    private final UserConverter converter;


    public User registerUser(String login,
                             String password,
                             String name,
                             String surname,
                             String patronymic,
                             String degree) {
        User user = buildUser(login, password, name, surname, patronymic, degree);
        repository.save(converter.convertEntityToDb(user));
        return user;
    }

    private User buildUser(String login,
                           String password,
                           String name,
                           String surname,
                           String patronymic,
                           String degree) {
        String securityPassword = encoder.encode(password);
        return new User(login,
                securityPassword,
                Optional.ofNullable(name).orElse(""),
                surname,
                Optional.ofNullable(patronymic).orElse(""),
                Optional.ofNullable(degree).orElse(""));
    }
}
