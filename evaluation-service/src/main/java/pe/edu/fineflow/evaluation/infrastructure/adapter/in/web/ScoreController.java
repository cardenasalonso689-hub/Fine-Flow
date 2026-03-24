package pe.edu.fineflow.evaluation.infrastructure.adapter.in.web;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.fineflow.evaluation.application.port.in.RegisterScoreUseCase;
import pe.edu.fineflow.evaluation.infrastructure.adapter.in.web.dto.ScoreDto;
import pe.edu.fineflow.evaluation.infrastructure.adapter.in.web.mapper.ScoreRestMapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/evaluation/scores")
@Tag(name = "Calificaciones", description = "Gestión de notas 0-20 por competencia MINEDU")
public class ScoreController {

    private final RegisterScoreUseCase useCase;
    private final ScoreRestMapper mapper;

    public ScoreController(RegisterScoreUseCase useCase, ScoreRestMapper mapper) {
        this.useCase = useCase; this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('TEACHER','ADMIN')")
    public Mono<ScoreDto.Response> register(@RequestBody ScoreDto.Create request) {
        return useCase.register(mapper.toDomain(request)).map(mapper::toResponse);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('TEACHER','ADMIN')")
    public Mono<ScoreDto.Response> update(@PathVariable String id, @RequestBody ScoreDto.Update request) {
        return useCase.update(id, mapper.toDomainUpdate(request)).map(mapper::toResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<Void> delete(@PathVariable String id) { return useCase.delete(id); }

    @GetMapping("/student/{studentId}")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR','TEACHER','STUDENT','GUARDIAN')")
    public Flux<ScoreDto.Response> findByStudent(@PathVariable String studentId) {
        return useCase.findByStudent(studentId).map(mapper::toResponse);
    }

    @GetMapping("/task/{classTaskId}")
    @PreAuthorize("hasAnyRole('TEACHER','ADMIN','COORDINATOR')")
    public Flux<ScoreDto.Response> findByTask(@PathVariable String classTaskId) {
        return useCase.findByClassTask(classTaskId).map(mapper::toResponse);
    }
}
