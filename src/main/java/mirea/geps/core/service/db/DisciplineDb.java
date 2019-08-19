package mirea.geps.core.service.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@Entity
@Table(name = "discipline")
@NoArgsConstructor
public class DisciplineDb {
    @Id
    @GeneratedValue
    private Long discipline_id;
    @Column
    private String code;
    @Column
    private String name;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<CompetitionDb> competitions;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ControlFormDb> controlForms;
    @Column
    private String exam_units;
    @Column
    private String lec_hours;
    @Column
    private String lab_hours;
    @Column
    private String prc_hours;
    @Column
    private String self_hours;
    @ManyToOne
    @JoinColumn(name = "educational_plan_id")
    private EducationalPlanDb planDb;
    @Column
    private String course;
}
