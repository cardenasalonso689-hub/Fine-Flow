package pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.mapper;

import org.mapstruct.Mapper;
import pe.edu.fineflow.academic.domain.model.CourseAssignment;
import pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.entity.CourseAssignmentEntity;

@Mapper(componentModel = "spring")
public interface CourseAssignmentMapper {
    CourseAssignmentEntity toEntity(CourseAssignment assignment);
    CourseAssignment toDomain(CourseAssignmentEntity entity);
}
