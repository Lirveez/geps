package mirea.geps.core.service.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@Entity(name = "educational_plan")
@Table(name = "educational_plan")
@NoArgsConstructor
public class EducationalPlanDb {

    @Id
    @GeneratedValue
    private Long educational_plan_id;
    @Column(unique = true)
    private String customName;
    @Column
    private String trDirection;
    @Column
    private String profile;
    @Column
    private String form;
    @Column
    private String plan;
    @Column
    private String department;
    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<DisciplineDb> disciplines;

}
