package mirea.geps.core.service.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EducationalPlanRepository extends JpaRepository<EducationalPlanDb, Long>{
    Optional<EducationalPlanDb> findByCustomName(String name);
}
