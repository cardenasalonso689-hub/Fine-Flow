package pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.mapper;

import org.mapstruct.Mapper;
import pe.edu.fineflow.academic.domain.model.CourseCompetency;
import pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.entity.CourseCompetencyEntity;

@Mapper(componentModel = "spring")
public interface CourseCompetencyMapper {
    CourseCompetencyEntity toEntity(CourseCompetency competency);
    CourseCompetency toDomain(CourseCompetencyEntity entity);
}
