package pe.edu.fineflow.academic.infrastructure.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.fineflow.academic.application.port.in.ManageCourseAssignmentUseCase;
import pe.edu.fineflow.academic.domain.model.CourseAssignment;
import pe.edu.fineflow.academic.infrastructure.adapter.in.web.dto.CourseAssignmentDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/academic/course-assignments")
@RequiredArgsConstructor
@Tag(name = "Asignaciones de Curso", description = "Gestión de asignaciones curso-docente-sección")
public class CourseAssignmentController {
    private final ManageCourseAssignmentUseCase useCase;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR')")
    public Flux<CourseAssignmentDto.Response> findAll() {
        return useCase.findAll().map(this::toResponse);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR','TEACHER')")
    public Mono<CourseAssignmentDto.Response> findById(@PathVariable String id) {
        return useCase.findById(id).map(this::toResponse);
    }

    @GetMapping("/section/{sectionId}")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR','TEACHER')")
    public Flux<CourseAssignmentDto.Response> findBySection(@PathVariable String sectionId) {
        return useCase.findBySection(sectionId).map(this::toResponse);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR')")
    public Mono<CourseAssignmentDto.Response> create(@Valid @RequestBody CourseAssignmentDto.Create request) {
        return useCase.create(toDomain(request)).map(this::toResponse);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR')")
    public Mono<CourseAssignmentDto.Response> update(@PathVariable String id, @Valid @RequestBody CourseAssignmentDto.Update request) {
        return useCase.update(id, toDomainUpdate(request)).map(this::toResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<Void> delete(@PathVariable String id) {
        return useCase.delete(id);
    }

    private CourseAssignmentDto.Response toResponse(CourseAssignment a) {
        return new CourseAssignmentDto.Response(a.getId(), a.getCourseId(), a.getSectionId(),
                a.getTeacherId(), a.getAcademicPeriodId(), a.getHoursPerWeek(), a.getIsActive());
    }

    private CourseAssignment toDomain(CourseAssignmentDto.Create dto) {
        CourseAssignment a = new CourseAssignment();
        a.setCourseId(dto.getCourseId());
        a.setSectionId(dto.getSectionId());
        a.setTeacherId(dto.getTeacherId());
        a.setAcademicPeriodId(dto.getAcademicPeriodId());
        a.setHoursPerWeek(dto.getHoursPerWeek());
        return a;
    }

    private CourseAssignment toDomainUpdate(CourseAssignmentDto.Update dto) {
        CourseAssignment a = new CourseAssignment();
        a.setCourseId(dto.getCourseId());
        a.setSectionId(dto.getSectionId());
        a.setTeacherId(dto.getTeacherId());
        a.setAcademicPeriodId(dto.getAcademicPeriodId());
        a.setHoursPerWeek(dto.getHoursPerWeek());
        a.setIsActive(dto.getIsActive());
        return a;
    }
}
