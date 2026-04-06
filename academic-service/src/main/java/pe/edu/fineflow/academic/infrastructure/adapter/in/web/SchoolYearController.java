package pe.edu.fineflow.academic.infrastructure.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.fineflow.academic.application.port.in.ManageSchoolYearUseCase;
import pe.edu.fineflow.academic.domain.model.SchoolYear;
import pe.edu.fineflow.academic.infrastructure.adapter.in.web.dto.SchoolYearDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/academic/school-years")
@RequiredArgsConstructor
@Tag(name = "Años Escolares", description = "Gestión de grados/años académicos")
public class SchoolYearController {
    private final ManageSchoolYearUseCase useCase;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR')")
    public Flux<SchoolYearDto.Response> findAll() {
        return useCase.findAll().map(this::toResponse);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR','TEACHER')")
    public Mono<SchoolYearDto.Response> findById(@PathVariable String id) {
        return useCase.findById(id).map(this::toResponse);
    }

    @GetMapping("/level/{academicLevelId}")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR')")
    public Flux<SchoolYearDto.Response> findByAcademicLevel(@PathVariable String academicLevelId) {
        return useCase.findByAcademicLevel(academicLevelId).map(this::toResponse);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<SchoolYearDto.Response> create(@Valid @RequestBody SchoolYearDto.Create request) {
        return useCase.create(toDomain(request)).map(this::toResponse);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<SchoolYearDto.Response> update(@PathVariable String id, @Valid @RequestBody SchoolYearDto.Update request) {
        return useCase.update(id, toDomainUpdate(request)).map(this::toResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<Void> delete(@PathVariable String id) {
        return useCase.delete(id);
    }

    private SchoolYearDto.Response toResponse(SchoolYear year) {
        return new SchoolYearDto.Response(year.getId(), year.getName(), year.getGradeNumber(),
                year.getCalendarYear(), year.getAcademicLevelId(), year.getIsActive());
    }

    private SchoolYear toDomain(SchoolYearDto.Create dto) {
        SchoolYear year = new SchoolYear();
        year.setName(dto.getName());
        year.setGradeNumber(dto.getGradeNumber());
        year.setCalendarYear(dto.getCalendarYear());
        year.setAcademicLevelId(dto.getAcademicLevelId());
        return year;
    }

    private SchoolYear toDomainUpdate(SchoolYearDto.Update dto) {
        SchoolYear year = new SchoolYear();
        year.setName(dto.getName());
        year.setGradeNumber(dto.getGradeNumber());
        year.setCalendarYear(dto.getCalendarYear());
        year.setAcademicLevelId(dto.getAcademicLevelId());
        year.setIsActive(dto.getIsActive());
        return year;
    }
}
