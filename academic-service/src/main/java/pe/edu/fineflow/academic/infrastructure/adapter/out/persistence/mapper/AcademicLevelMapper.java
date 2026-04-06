package pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.mapper;

import org.mapstruct.Mapper;
import pe.edu.fineflow.academic.domain.model.AcademicLevel;
import pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.entity.AcademicLevelEntity;

@Mapper(componentModel = "spring")
public interface AcademicLevelMapper {
    AcademicLevelEntity toEntity(AcademicLevel level);
    AcademicLevel toDomain(AcademicLevelEntity entity);
}
