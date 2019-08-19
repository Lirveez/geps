package mirea.geps.core.service.db;

import mirea.geps.core.dto.Competition;
import mirea.geps.core.dto.ControlForm;
import mirea.geps.core.dto.Discipline;
import mirea.geps.core.dto.EducationalPlan;
import org.assertj.core.util.Sets;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EducationalPlanCustomTest {

    @Autowired
    private EducationalPlanRepositoryCustom repository;

    private Discipline discipline1;
    private Discipline discipline2;
    private EducationalPlan educationalPlan;

    @Before
    public void setUp() throws Exception {
        repository.deleteAll();
        Competition competition1 = new Competition("compCode1", "desc1", "type1");
        ControlForm form1 = new ControlForm("cfname1");
        discipline1 = new Discipline(
                "code1",
                "name1",
                Sets.newLinkedHashSet(competition1),
                Sets.newLinkedHashSet(form1),
                "exam_units1",
                "lec_hours1",
                "lab_hours1",
                "prc_hours1",
                "self_hours1",
                "1");
        Competition competition2 = new Competition("compCode2", "desc2", "type2");
        discipline2 = new Discipline(
                "code2",
                "name2",
                Sets.newLinkedHashSet(competition1, competition2),
                Sets.newLinkedHashSet(form1),
                "exam_units2",
                "lec_hours2",
                "lab_hours2",
                "prc_hour2",
                "self_hours2",
                "2");
        educationalPlan = new EducationalPlan(
                "customName",
                "trDirection",
                "profile",
                "form",
                "plan",
                "department",
                Sets.newLinkedHashSet(discipline1, discipline2)
        );
        repository.save(educationalPlan);
    }

    @Test
    public void shouldFindEducationalPlan() {
        EducationalPlan actual = repository.findPlanByCustomName("customName").get();

        assertThat(actual, is(educationalPlan));
        assertThat(actual.getDisciplines(),is(Sets.newLinkedHashSet(discipline1, discipline2)));
    }

    @Test
    public void shouldReturnOptionalEmptyIfPlanIsNotFoud() {
        Optional<EducationalPlan> empty = repository.findPlanByCustomName("123");

        assertFalse(empty.isPresent());
    }
}