package pe.edu.fineflow.profile.infrastructure.adapter.out.persistence.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import pe.edu.fineflow.profile.domain.model.Student;
import pe.edu.fineflow.profile.infrastructure.adapter.out.persistence.entity.StudentEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentPersistenceMapper {
    Student toDomain(StudentEntity entity);
    StudentEntity toEntity(Student domain);
}
