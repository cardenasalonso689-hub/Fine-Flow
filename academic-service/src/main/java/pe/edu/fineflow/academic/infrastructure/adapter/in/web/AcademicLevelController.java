package pe.edu.fineflow.academic.infrastructure.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.fineflow.academic.application.port.in.ManageAcademicLevelUseCase;
import pe.edu.fineflow.academic.domain.model.AcademicLevel;
import pe.edu.fineflow.academic.infrastructure.adapter.in.web.dto.AcademicLevelDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/academic/levels")
@RequiredArgsConstructor
@Tag(name = "Niveles Académicos", description = "Gestión de niveles educativos")
public class AcademicLevelController {
    private final ManageAcademicLevelUseCase useCase;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR')")
    public Flux<AcademicLevelDto.Response> findAll() {
        return useCase.findAll().map(this::toResponse);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR')")
    public Mono<AcademicLevelDto.Response> findById(@PathVariable String id) {
        return useCase.findById(id).map(this::toResponse);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<AcademicLevelDto.Response> create(@Valid @RequestBody AcademicLevelDto.Create request) {
        return useCase.create(toDomain(request)).map(this::toResponse);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<AcademicLevelDto.Response> update(@PathVariable String id, @Valid @RequestBody AcademicLevelDto.Update request) {
        return useCase.update(id, toDomainUpdate(request)).map(this::toResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<Void> delete(@PathVariable String id) {
        return useCase.delete(id);
    }

    private AcademicLevelDto.Response toResponse(AcademicLevel level) {
        return new AcademicLevelDto.Response(level.getId(), level.getName(), level.getOrderNum(), level.getIsActive());
    }

    private AcademicLevel toDomain(AcademicLevelDto.Create dto) {
        AcademicLevel level = new AcademicLevel();
        level.setName(dto.getName());
        level.setOrderNum(dto.getOrderNum());
        return level;
    }

    private AcademicLevel toDomainUpdate(AcademicLevelDto.Update dto) {
        AcademicLevel level = new AcademicLevel();
        level.setName(dto.getName());
        level.setOrderNum(dto.getOrderNum());
        level.setIsActive(dto.getIsActive());
        return level;
    }
}
