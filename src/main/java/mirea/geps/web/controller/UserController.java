package mirea.geps.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import mirea.geps.core.dto.User;
import mirea.geps.core.service.AuthService;
import mirea.geps.core.service.RegisterService;
import mirea.geps.web.dto.AuthUserRequest;
import mirea.geps.web.dto.AuthUserResponse;
import mirea.geps.web.dto.RegisterUserRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/eps/user")
public class UserController {

    private final RegisterService registerService;
    private final AuthService authService;

    @PostMapping(value = "/register")
    @ApiOperation("Регистрация пользователя")
    public User registerUser(@RequestBody @Valid @NotNull RegisterUserRequest request) {
        return registerService.registerUser(
                request.getLogin(),
                request.getPassword(),
                request.getName(),
                request.getSurname(),
                request.getPatronymic(),
                request.getDegree());
    }

    @PostMapping(value = "/auth")
    @ApiOperation("Авторизация пользователя по логину и паролю")
    public AuthUserResponse authUser(@RequestBody @Valid @NotNull AuthUserRequest request) {
        AuthUserResponse response = new AuthUserResponse();

        User user = authService.authUser(request.getLogin(), request.getPassword());

        if (user != null) {
            response.setStatus("SUCCESS");
            response.setUser(user);
        }

        return response;
    }
}

