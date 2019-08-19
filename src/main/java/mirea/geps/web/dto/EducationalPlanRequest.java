package mirea.geps.web.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EducationalPlanRequest {
    @NotBlank
    private String name;
}
