package pe.edu.fineflow.academic.infrastructure.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.fineflow.academic.application.port.in.ManageSectionUseCase;
import pe.edu.fineflow.academic.domain.model.Section;
import pe.edu.fineflow.academic.infrastructure.adapter.in.web.dto.SectionDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/academic/sections")
@RequiredArgsConstructor
@Tag(name = "Secciones", description = "Gestión de aulas/secciones")
public class SectionController {
    private final ManageSectionUseCase useCase;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR','TEACHER')")
    public Flux<SectionDto.Response> findAll() {
        return useCase.findAll().map(this::toResponse);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR','TEACHER')")
    public Mono<SectionDto.Response> findById(@PathVariable String id) {
        return useCase.findById(id).map(this::toResponse);
    }

    @GetMapping("/school-year/{schoolYearId}")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR','TEACHER')")
    public Flux<SectionDto.Response> findBySchoolYear(@PathVariable String schoolYearId) {
        return useCase.findBySchoolYear(schoolYearId).map(this::toResponse);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<SectionDto.Response> create(@Valid @RequestBody SectionDto.Create request) {
        return useCase.create(toDomain(request)).map(this::toResponse);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<SectionDto.Response> update(@PathVariable String id, @Valid @RequestBody SectionDto.Update request) {
        return useCase.update(id, toDomainUpdate(request)).map(this::toResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<Void> delete(@PathVariable String id) {
        return useCase.delete(id);
    }

    private SectionDto.Response toResponse(Section section) {
        return new SectionDto.Response(section.getId(), section.getName(), section.getMaxCapacity(),
                section.getTutorId(), section.getIsActive());
    }

    private Section toDomain(SectionDto.Create dto) {
        Section section = new Section();
        section.setName(dto.getName());
        section.setMaxCapacity(dto.getMaxCapacity());
        section.setSchoolYearId(dto.getSchoolYearId());
        return section;
    }

    private Section toDomainUpdate(SectionDto.Update dto) {
        Section section = new Section();
        section.setName(dto.getName());
        section.setMaxCapacity(dto.getMaxCapacity());
        section.setTutorId(dto.getTutorId());
        section.setIsActive(dto.getIsActive());
        return section;
    }
}
