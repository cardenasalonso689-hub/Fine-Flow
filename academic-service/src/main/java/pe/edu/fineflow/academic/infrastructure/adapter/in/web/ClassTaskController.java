package pe.edu.fineflow.academic.infrastructure.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.fineflow.academic.application.port.in.ManageClassTaskUseCase;
import pe.edu.fineflow.academic.domain.model.ClassTask;
import pe.edu.fineflow.academic.infrastructure.adapter.in.web.dto.ClassTaskDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/academic/tasks")
@RequiredArgsConstructor
@Tag(name = "Tareas Evaluables", description = "Gestión de actividades evaluables")
public class ClassTaskController {
    private final ManageClassTaskUseCase useCase;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR','TEACHER')")
    public Flux<ClassTaskDto.Response> findAll() {
        return useCase.findAll().map(this::toResponse);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR','TEACHER')")
    public Mono<ClassTaskDto.Response> findById(@PathVariable String id) {
        return useCase.findById(id).map(this::toResponse);
    }

    @GetMapping("/assignment/{courseAssignmentId}")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR','TEACHER')")
    public Flux<ClassTaskDto.Response> findByCourseAssignment(@PathVariable String courseAssignmentId) {
        return useCase.findByCourseAssignment(courseAssignmentId).map(this::toResponse);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR','TEACHER')")
    public Mono<ClassTaskDto.Response> create(@Valid @RequestBody ClassTaskDto.Create request) {
        return useCase.create(toDomain(request)).map(this::toResponse);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR','TEACHER')")
    public Mono<ClassTaskDto.Response> update(@PathVariable String id, @Valid @RequestBody ClassTaskDto.Update request) {
        return useCase.update(id, toDomainUpdate(request)).map(this::toResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR')")
    public Mono<Void> delete(@PathVariable String id) {
        return useCase.delete(id);
    }

    private ClassTaskDto.Response toResponse(ClassTask t) {
        return new ClassTaskDto.Response(t.getId(), t.getTitle(), t.getDescription(), t.getTaskType(),
                t.getMaxScore(), t.getDueDate(), t.getIsActive());
    }

    private ClassTask toDomain(ClassTaskDto.Create dto) {
        ClassTask t = new ClassTask();
        t.setTitle(dto.getTitle());
        t.setDescription(dto.getDescription());
        t.setTaskType(dto.getTaskType());
        t.setMaxScore(dto.getMaxScore());
        t.setDueDate(dto.getDueDate());
        t.setCourseAssignmentId(dto.getCourseAssignmentId());
        t.setCompetencyId(dto.getCompetencyId());
        t.setAcademicPeriodId(dto.getAcademicPeriodId());
        return t;
    }

    private ClassTask toDomainUpdate(ClassTaskDto.Update dto) {
        ClassTask t = new ClassTask();
        t.setTitle(dto.getTitle());
        t.setDescription(dto.getDescription());
        t.setTaskType(dto.getTaskType());
        t.setMaxScore(dto.getMaxScore());
        t.setDueDate(dto.getDueDate());
        t.setIsActive(dto.getIsActive());
        return t;
    }
}
