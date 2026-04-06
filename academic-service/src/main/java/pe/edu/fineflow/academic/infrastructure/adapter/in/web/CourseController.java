package pe.edu.fineflow.academic.infrastructure.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.fineflow.academic.application.port.in.ManageCourseUseCase;
import pe.edu.fineflow.academic.domain.model.Course;
import pe.edu.fineflow.academic.infrastructure.adapter.in.web.dto.CourseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/academic/courses")
@RequiredArgsConstructor
@Tag(name = "Cursos", description = "Gestión de áreas/cursos curriculares")
public class CourseController {
    private final ManageCourseUseCase useCase;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR','TEACHER')")
    public Flux<CourseDto.Response> findAll() {
        return useCase.findAll().map(this::toResponse);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR','TEACHER')")
    public Mono<CourseDto.Response> findById(@PathVariable String id) {
        return useCase.findById(id).map(this::toResponse);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<CourseDto.Response> create(@Valid @RequestBody CourseDto.Create request) {
        return useCase.create(toDomain(request)).map(this::toResponse);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<CourseDto.Response> update(@PathVariable String id, @Valid @RequestBody CourseDto.Update request) {
        return useCase.update(id, toDomainUpdate(request)).map(this::toResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<Void> delete(@PathVariable String id) {
        return useCase.delete(id);
    }

    private CourseDto.Response toResponse(Course course) {
        return new CourseDto.Response(course.getId(), course.getName(), course.getCode(),
                course.getDescription(), course.getColorHex(), course.getIsActive());
    }

    private Course toDomain(CourseDto.Create dto) {
        Course course = new Course();
        course.setName(dto.getName());
        course.setCode(dto.getCode());
        course.setDescription(dto.getDescription());
        course.setColorHex(dto.getColorHex());
        return course;
    }

    private Course toDomainUpdate(CourseDto.Update dto) {
        Course course = new Course();
        course.setName(dto.getName());
        course.setCode(dto.getCode());
        course.setDescription(dto.getDescription());
        course.setColorHex(dto.getColorHex());
        course.setIsActive(dto.getIsActive());
        return course;
    }
}
