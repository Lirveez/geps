package mirea.geps.web.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import mirea.geps.core.dto.User;

@Data
public class AuthUserResponse {

    private String status = "ERROR";
    @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
    private User user;
}
