package pe.edu.fineflow.profile.infrastructure.adapter.out.persistence.mapper;

import org.mapstruct.Mapper;
import pe.edu.fineflow.profile.domain.model.Guardian;
import pe.edu.fineflow.profile.infrastructure.adapter.out.persistence.entity.GuardianEntity;

@Mapper(componentModel = "spring")
public interface GuardianPersistenceMapper {
    GuardianEntity toEntity(Guardian guardian);
    Guardian toDomain(GuardianEntity entity);
}
