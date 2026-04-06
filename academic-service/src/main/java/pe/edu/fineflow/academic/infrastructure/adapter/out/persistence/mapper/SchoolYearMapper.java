package pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.mapper;

import org.mapstruct.Mapper;
import pe.edu.fineflow.academic.domain.model.SchoolYear;
import pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.entity.SchoolYearEntity;

@Mapper(componentModel = "spring")
public interface SchoolYearMapper {
    SchoolYearEntity toEntity(SchoolYear year);
    SchoolYear toDomain(SchoolYearEntity entity);
}
