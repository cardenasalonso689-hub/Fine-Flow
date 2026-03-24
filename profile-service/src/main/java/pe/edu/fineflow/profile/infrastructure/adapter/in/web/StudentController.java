package pe.edu.fineflow.profile.infrastructure.adapter.in.web;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.fineflow.profile.application.port.in.ManageStudentUseCase;
import pe.edu.fineflow.profile.infrastructure.adapter.in.web.dto.StudentDto;
import pe.edu.fineflow.profile.infrastructure.adapter.in.web.mapper.StudentRestMapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/profile/students")
@Tag(name = "Estudiantes", description = "Gestión de estudiantes (multi-tenant)")
public class StudentController {

    private final ManageStudentUseCase useCase;
    private final StudentRestMapper mapper;

    public StudentController(ManageStudentUseCase useCase, StudentRestMapper mapper) {
        this.useCase = useCase;
        this.mapper  = mapper;
    }

    @GetMapping
    @Operation(summary = "Listar estudiantes del colegio autenticado")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR','TEACHER')")
    public Flux<StudentDto.Response> findAll() {
        return useCase.search("").map(mapper::toResponse);
    }

    @GetMapping("/section/{sectionId}")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR','TEACHER')")
    public Flux<StudentDto.Response> findBySection(@PathVariable String sectionId) {
        return useCase.findAllBySection(sectionId).map(mapper::toResponse);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR','TEACHER','STUDENT')")
    public Mono<StudentDto.Response> findById(@PathVariable String id) {
        return useCase.findById(id).map(mapper::toResponse);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR')")
    public Mono<StudentDto.Response> create(@RequestBody StudentDto.Create request) {
        return useCase.create(mapper.toDomain(request)).map(mapper::toResponse);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR')")
    public Mono<StudentDto.Response> update(@PathVariable String id,
                                            @RequestBody StudentDto.Update request) {
        return useCase.update(id, mapper.toDomainUpdate(request)).map(mapper::toResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<Void> delete(@PathVariable String id) {
        return useCase.delete(id);
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR','TEACHER')")
    public Flux<StudentDto.Response> search(@RequestParam String q) {
        return useCase.search(q).map(mapper::toResponse);
    }
}
