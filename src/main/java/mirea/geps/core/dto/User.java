package mirea.geps.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String login;
    @JsonIgnore
    private String password;
    private String name;
    private String surname;
    private String patronymic;
    private String degree;

}
