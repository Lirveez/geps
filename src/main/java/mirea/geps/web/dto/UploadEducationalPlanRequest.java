package mirea.geps.web.dto;

import lombok.Data;
import mirea.geps.core.dto.Discipline;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class UploadEducationalPlanRequest {

    @NotBlank
    private String customName;
    @NotBlank
    private String trDirection;
    @NotBlank
    private String profile;
    @NotBlank
    private String form;
    @NotBlank
    private String plan;
    @NotBlank
    private String department;
    @NotEmpty
    private Set<Discipline> disciplines;
}
