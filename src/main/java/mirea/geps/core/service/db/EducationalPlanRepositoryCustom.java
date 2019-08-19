package mirea.geps.core.service.db;

import mirea.geps.core.dto.EducationalPlan;

import java.util.List;
import java.util.Optional;

public interface EducationalPlanRepositoryCustom {
    EducationalPlan save(EducationalPlan plan);
    List<EducationalPlan> findAll();
    void deleteAll();
    Optional<EducationalPlan> findPlanByCustomName(String name);
}
