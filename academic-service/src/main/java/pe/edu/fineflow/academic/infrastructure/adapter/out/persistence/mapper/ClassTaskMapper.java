package pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.mapper;

import org.mapstruct.Mapper;
import pe.edu.fineflow.academic.domain.model.ClassTask;
import pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.entity.ClassTaskEntity;

@Mapper(componentModel = "spring")
public interface ClassTaskMapper {
    ClassTaskEntity toEntity(ClassTask task);
    ClassTask toDomain(ClassTaskEntity entity);
}
