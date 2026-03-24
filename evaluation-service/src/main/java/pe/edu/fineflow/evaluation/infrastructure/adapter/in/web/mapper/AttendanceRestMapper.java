package pe.edu.fineflow.evaluation.infrastructure.adapter.in.web.mapper;
import org.mapstruct.Mapper; import org.mapstruct.MappingConstants;
import pe.edu.fineflow.evaluation.domain.model.Attendance;
import pe.edu.fineflow.evaluation.infrastructure.adapter.in.web.dto.AttendanceDto;
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AttendanceRestMapper {
    Attendance toDomain(AttendanceDto.Create dto);
    AttendanceDto.Response toResponse(Attendance domain);
}
