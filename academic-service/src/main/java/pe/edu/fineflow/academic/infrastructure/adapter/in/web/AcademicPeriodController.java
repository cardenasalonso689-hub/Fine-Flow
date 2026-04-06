package pe.edu.fineflow.academic.infrastructure.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.fineflow.academic.application.port.in.ManageAcademicPeriodUseCase;
import pe.edu.fineflow.academic.domain.model.AcademicPeriod;
import pe.edu.fineflow.academic.infrastructure.adapter.in.web.dto.AcademicPeriodDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/academic/periods")
@RequiredArgsConstructor
@Tag(name = "Períodos Académicos", description = "Gestión de bimestres, trimestres, semestres")
public class AcademicPeriodController {
    private final ManageAcademicPeriodUseCase useCase;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR','TEACHER')")
    public Flux<AcademicPeriodDto.Response> findAll() {
        return useCase.findAll().map(this::toResponse);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR','TEACHER')")
    public Mono<AcademicPeriodDto.Response> findById(@PathVariable String id) {
        return useCase.findById(id).map(this::toResponse);
    }

    @GetMapping("/school-year/{schoolYearId}")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR','TEACHER')")
    public Flux<AcademicPeriodDto.Response> findBySchoolYear(@PathVariable String schoolYearId) {
        return useCase.findBySchoolYear(schoolYearId).map(this::toResponse);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<AcademicPeriodDto.Response> create(@Valid @RequestBody AcademicPeriodDto.Create request) {
        return useCase.create(toDomain(request)).map(this::toResponse);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<AcademicPeriodDto.Response> update(@PathVariable String id, @Valid @RequestBody AcademicPeriodDto.Update request) {
        return useCase.update(id, toDomainUpdate(request)).map(this::toResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<Void> delete(@PathVariable String id) {
        return useCase.delete(id);
    }

    private AcademicPeriodDto.Response toResponse(AcademicPeriod period) {
        return new AcademicPeriodDto.Response(period.getId(), period.getName(), period.getPeriodType(),
                period.getStartDate(), period.getEndDate(), period.getIsActive());
    }

    private AcademicPeriod toDomain(AcademicPeriodDto.Create dto) {
        AcademicPeriod period = new AcademicPeriod();
        period.setName(dto.getName());
        period.setPeriodType(dto.getPeriodType());
        period.setStartDate(dto.getStartDate());
        period.setEndDate(dto.getEndDate());
        period.setSchoolYearId(dto.getSchoolYearId());
        return period;
    }

    private AcademicPeriod toDomainUpdate(AcademicPeriodDto.Update dto) {
        AcademicPeriod period = new AcademicPeriod();
        period.setName(dto.getName());
        period.setPeriodType(dto.getPeriodType());
        period.setStartDate(dto.getStartDate());
        period.setEndDate(dto.getEndDate());
        period.setIsActive(dto.getIsActive());
        return period;
    }
}
