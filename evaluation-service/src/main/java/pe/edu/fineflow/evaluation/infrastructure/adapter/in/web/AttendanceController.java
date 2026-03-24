package pe.edu.fineflow.evaluation.infrastructure.adapter.in.web;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.fineflow.evaluation.application.port.in.RecordAttendanceUseCase;
import pe.edu.fineflow.evaluation.infrastructure.adapter.in.web.dto.AttendanceDto;
import pe.edu.fineflow.evaluation.infrastructure.adapter.in.web.mapper.AttendanceRestMapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/evaluation/attendances")
@Tag(name = "Asistencia", description = "Registro de asistencia por clase o entrada general")
public class AttendanceController {

    private final RecordAttendanceUseCase useCase;
    private final AttendanceRestMapper mapper;

    public AttendanceController(RecordAttendanceUseCase useCase, AttendanceRestMapper mapper) {
        this.useCase = useCase; this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('TEACHER','ADMIN','COORDINATOR')")
    @Operation(summary = "Registrar asistencia individual")
    public Mono<AttendanceDto.Response> record(@RequestBody AttendanceDto.Create request) {
        return useCase.recordSingle(mapper.toDomain(request)).map(mapper::toResponse);
    }

    @PostMapping("/bulk")
    @PreAuthorize("hasAnyRole('TEACHER','ADMIN')")
    @Operation(summary = "Registrar asistencia masiva de una sección")
    public Flux<AttendanceDto.Response> recordBulk(@RequestBody List<AttendanceDto.Create> requests) {
        return useCase.recordBulk(requests.stream().map(mapper::toDomain).toList()).map(mapper::toResponse);
    }

    @PostMapping("/qr")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registrar entrada por QR del carnet (público)")
    public Mono<AttendanceDto.Response> recordQr(@RequestParam String token,
                                                  @RequestParam String schoolId) {
        return useCase.recordQrEntry(token, schoolId).map(mapper::toResponse);
    }

    @GetMapping("/student/{studentId}")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR','TEACHER','GUARDIAN','STUDENT')")
    public Flux<AttendanceDto.Response> findByStudent(@PathVariable String studentId) {
        return useCase.findByStudent(studentId).map(mapper::toResponse);
    }

    @GetMapping("/date/{date}")
    @PreAuthorize("hasAnyRole('ADMIN','COORDINATOR','TEACHER')")
    public Flux<AttendanceDto.Response> findByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return useCase.findByDate(date).map(mapper::toResponse);
    }
}
