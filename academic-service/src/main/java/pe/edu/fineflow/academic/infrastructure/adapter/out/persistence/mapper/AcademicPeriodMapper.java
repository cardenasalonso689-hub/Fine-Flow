package pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.mapper;

import org.mapstruct.Mapper;
import pe.edu.fineflow.academic.domain.model.AcademicPeriod;
import pe.edu.fineflow.academic.infrastructure.adapter.out.persistence.entity.AcademicPeriodEntity;

@Mapper(componentModel = "spring")
public interface AcademicPeriodMapper {
    AcademicPeriodEntity toEntity(AcademicPeriod period);
    AcademicPeriod toDomain(AcademicPeriodEntity entity);
}
