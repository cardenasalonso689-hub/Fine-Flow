package pe.edu.fineflow.profile.infrastructure.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.fineflow.profile.application.port.in.ManageGuardianUseCase;
import pe.edu.fineflow.profile.domain.model.Guardian;
import pe.edu.fineflow.profile.infrastructure.adapter.in.web.dto.GuardianDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/profile/guardians")
@RequiredArgsConstructor
@Tag(name = "Apoderados", description = "Gestión de apoderados/tutores")
public class GuardianController {
    private final ManageGuardianUseCase useCase;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR')")
    public Flux<GuardianDto.Response> findAll() {
        return useCase.findAll().map(this::toResponse);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR','TEACHER')")
    public Mono<GuardianDto.Response> findById(@PathVariable String id) {
        return useCase.findById(id).map(this::toResponse);
    }

    @GetMapping("/student/{studentId}")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR','TEACHER')")
    public Flux<GuardianDto.Response> findByStudent(@PathVariable String studentId) {
        return useCase.findByStudent(studentId).map(this::toResponse);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR')")
    public Mono<GuardianDto.Response> create(@Valid @RequestBody GuardianDto.Create request) {
        return useCase.create(toDomain(request)).map(this::toResponse);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR')")
    public Mono<GuardianDto.Response> update(@PathVariable String id, @Valid @RequestBody GuardianDto.Update request) {
        return useCase.update(id, toDomainUpdate(request)).map(this::toResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<Void> delete(@PathVariable String id) {
        return useCase.delete(id);
    }

    private GuardianDto.Response toResponse(Guardian g) {
        return new GuardianDto.Response(g.getId(), g.getFirstName(), g.getLastName(), g.getRelationship(),
                g.getPhone(), g.getDocumentNumber(), g.getEmail(), g.isPrimaryContact(), g.getStudentId());
    }

    private Guardian toDomain(GuardianDto.Create dto) {
        Guardian g = new Guardian();
        g.setFirstName(dto.getFirstName());
        g.setLastName(dto.getLastName());
        g.setRelationship(dto.getRelationship());
        g.setPhone(dto.getPhone());
        g.setDocumentNumber(dto.getDocumentNumber());
        g.setEmail(dto.getEmail());
        g.setPrimaryContact(dto.isPrimaryContact());
        g.setStudentId(dto.getStudentId());
        return g;
    }

    private Guardian toDomainUpdate(GuardianDto.Update dto) {
        Guardian g = new Guardian();
        g.setFirstName(dto.getFirstName());
        g.setLastName(dto.getLastName());
        g.setRelationship(dto.getRelationship());
        g.setPhone(dto.getPhone());
        g.setDocumentNumber(dto.getDocumentNumber());
        g.setEmail(dto.getEmail());
        g.setPrimaryContact(dto.isPrimaryContact());
        return g;
    }
}
