package pe.edu.fineflow.profile.infrastructure.adapter.in.web.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import pe.edu.fineflow.profile.domain.model.Student;
import pe.edu.fineflow.profile.infrastructure.adapter.in.web.dto.StudentDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentRestMapper {
    Student toDomain(StudentDto.Create dto);
    Student toDomainUpdate(StudentDto.Update dto);
    StudentDto.Response toResponse(Student student);
}
