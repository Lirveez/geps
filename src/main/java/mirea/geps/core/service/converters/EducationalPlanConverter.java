package mirea.geps.core.service.converters;

import lombok.RequiredArgsConstructor;
import mirea.geps.core.dto.Discipline;
import mirea.geps.core.dto.EducationalPlan;
import mirea.geps.core.service.db.DisciplineDb;
import mirea.geps.core.service.db.EducationalPlanDb;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EducationalPlanConverter {

    private final DisciplineConverter disciplineConverter;

    public EducationalPlan convertToEntity(EducationalPlanDb educationalPlanDb) {
        EducationalPlan educationalPlan = new EducationalPlan();
        BeanUtils.copyProperties(educationalPlanDb, educationalPlan);
        Set<Discipline> disciplines = educationalPlanDb.getDisciplines().stream()
                .map(disciplineConverter::convertToEntity)
                .collect(Collectors.toSet());
        educationalPlan.setDisciplines(disciplines);
        return educationalPlan;

    }

    public EducationalPlanDb convertToDb(EducationalPlan educationalPlan) {
        EducationalPlanDb educationalPlanDb = new EducationalPlanDb();
        BeanUtils.copyProperties(educationalPlan, educationalPlanDb);
        Set<DisciplineDb> disciplineDbs = educationalPlan.getDisciplines().stream()
                .map(disciplineConverter::convertEntityToDb)
                .collect(Collectors.toSet());

        educationalPlanDb.setDisciplines(disciplineDbs);
        return educationalPlanDb;
    }
}
