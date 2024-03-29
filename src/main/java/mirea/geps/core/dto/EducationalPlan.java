package mirea.geps.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationalPlan {

    private String customName;
    private String trDirection;
    private String profile;
    private String form;
    private String plan;
    private String department;
    private Set<Discipline> disciplines;
}
