package pe.edu.fineflow.academic.infrastructure.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.fineflow.academic.application.port.in.ManageCourseCompetencyUseCase;
import pe.edu.fineflow.academic.domain.model.CourseCompetency;
import pe.edu.fineflow.academic.infrastructure.adapter.in.web.dto.CourseCompetencyDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/academic/competencies")
@RequiredArgsConstructor
@Tag(name = "Competencias", description = "Gestión de competencias curriculares MINEDU")
public class CourseCompetencyController {
    private final ManageCourseCompetencyUseCase useCase;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR','TEACHER')")
    public Flux<CourseCompetencyDto.Response> findAll() {
        return useCase.findAll().map(this::toResponse);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR','TEACHER')")
    public Mono<CourseCompetencyDto.Response> findById(@PathVariable String id) {
        return useCase.findById(id).map(this::toResponse);
    }

    @GetMapping("/course/{courseId}")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR','TEACHER')")
    public Flux<CourseCompetencyDto.Response> findByCourse(@PathVariable String courseId) {
        return useCase.findByCourse(courseId).map(this::toResponse);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR')")
    public Mono<CourseCompetencyDto.Response> create(@Valid @RequestBody CourseCompetencyDto.Create request) {
        return useCase.create(toDomain(request)).map(this::toResponse);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR')")
    public Mono<CourseCompetencyDto.Response> update(@PathVariable String id, @Valid @RequestBody CourseCompetencyDto.Update request) {
        return useCase.update(id, toDomainUpdate(request)).map(this::toResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<Void> delete(@PathVariable String id) {
        return useCase.delete(id);
    }

    private CourseCompetencyDto.Response toResponse(CourseCompetency c) {
        return new CourseCompetencyDto.Response(c.getId(), c.getName(), c.getDescription(), c.getWeight(), c.getIsActive());
    }

    private CourseCompetency toDomain(CourseCompetencyDto.Create dto) {
        CourseCompetency c = new CourseCompetency();
        c.setName(dto.getName());
        c.setDescription(dto.getDescription());
        c.setWeight(dto.getWeight());
        c.setCourseId(dto.getCourseId());
        return c;
    }

    private CourseCompetency toDomainUpdate(CourseCompetencyDto.Update dto) {
        CourseCompetency c = new CourseCompetency();
        c.setName(dto.getName());
        c.setDescription(dto.getDescription());
        c.setWeight(dto.getWeight());
        c.setIsActive(dto.getIsActive());
        return c;
    }
}
