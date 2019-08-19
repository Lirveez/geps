package mirea.geps.web.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RegisterUserRequest {

    @NotBlank(message = "Login must be not empty")
    private String login;

    @NotBlank(message = "Password must be not empty")
    private String password;

    private String name;

    @NotBlank(message = "Surname must be not empty")
    private String surname;

    private String patronymic;
    
    private String degree;

}
