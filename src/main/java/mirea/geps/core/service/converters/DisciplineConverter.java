package mirea.geps.core.service.converters;

import lombok.AllArgsConstructor;
import mirea.geps.core.dto.Competition;
import mirea.geps.core.dto.ControlForm;
import mirea.geps.core.dto.Discipline;
import mirea.geps.core.service.db.CompetitionDb;
import mirea.geps.core.service.db.ControlFormDb;
import mirea.geps.core.service.db.DisciplineDb;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class DisciplineConverter {

    public DisciplineDb convertEntityToDb(Discipline discipline) {
        DisciplineDb disciplineDb = new DisciplineDb();
        BeanUtils.copyProperties(discipline, disciplineDb);
        Set<CompetitionDb> competitionDbs = discipline.getCompetitions().stream()
                .map(competition -> {
                    CompetitionDb competitionDb = new CompetitionDb();
                    BeanUtils.copyProperties(competition, competitionDb);
                    return competitionDb;
                })
                .collect(Collectors.toSet());
        Set<ControlFormDb> controlFormDbs = discipline.getControlForms().stream()
                .map(controlForm -> {
                    ControlFormDb controlFormDb = new ControlFormDb();
                    BeanUtils.copyProperties(controlForm, controlFormDb);
                    return controlFormDb;
                })
                .collect(Collectors.toSet());
        disciplineDb.setCompetitions(competitionDbs);
        disciplineDb.setControlForms(controlFormDbs);
        return disciplineDb;
    }

    public Discipline convertToEntity(DisciplineDb disciplineDb) {
        Discipline discipline = new Discipline();
        BeanUtils.copyProperties(disciplineDb, discipline);
        Set<Competition> competitions = disciplineDb.getCompetitions().stream()
                .map(competitionDb -> {
                    Competition competition = new Competition();
                    BeanUtils.copyProperties(competitionDb, competition);
                    return competition;
                })
                .collect(Collectors.toSet());
        Set<ControlForm> controlForms = disciplineDb.getControlForms().stream()
                .map(controlFormDb -> {
                    ControlForm controlForm = new ControlForm();
                    BeanUtils.copyProperties(controlFormDb, controlForm);
                    return controlForm;
                })
                .collect(Collectors.toSet());
        discipline.setCompetitions(competitions);
        discipline.setControlForms(controlForms);
        return discipline;
    }
}
