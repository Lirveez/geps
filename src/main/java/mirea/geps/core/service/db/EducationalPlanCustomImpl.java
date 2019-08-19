package mirea.geps.core.service.db;

import lombok.RequiredArgsConstructor;
import mirea.geps.core.dto.EducationalPlan;
import mirea.geps.core.service.converters.EducationalPlanConverter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EducationalPlanCustomImpl implements EducationalPlanRepositoryCustom {

    private final EducationalPlanConverter converter;
    private final EducationalPlanRepository repository;

    @Override
    public EducationalPlan save(EducationalPlan plan) {
        EducationalPlanDb educationalPlanDb = converter.convertToDb(plan);
        EducationalPlanDb saved = repository.saveAndFlush(educationalPlanDb);
        return converter.convertToEntity(saved);
    }

    @Override
    public List<EducationalPlan> findAll() {
        return repository.findAll().stream()
                .map(converter::convertToEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public Optional<EducationalPlan> findPlanByCustomName(String name) {
        return repository.findByCustomName(name).map(converter::convertToEntity);
    }

}
