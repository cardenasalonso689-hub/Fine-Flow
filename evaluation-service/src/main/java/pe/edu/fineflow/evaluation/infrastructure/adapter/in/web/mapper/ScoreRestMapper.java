package pe.edu.fineflow.evaluation.infrastructure.adapter.in.web.mapper;
import org.mapstruct.Mapper; import org.mapstruct.MappingConstants;
import pe.edu.fineflow.evaluation.domain.model.StudentScore;
import pe.edu.fineflow.evaluation.infrastructure.adapter.in.web.dto.ScoreDto;
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ScoreRestMapper {
    StudentScore toDomain(ScoreDto.Create dto);
    StudentScore toDomainUpdate(ScoreDto.Update dto);
    ScoreDto.Response toResponse(StudentScore domain);
}
