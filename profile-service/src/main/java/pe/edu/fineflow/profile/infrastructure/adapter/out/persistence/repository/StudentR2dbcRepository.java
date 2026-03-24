package pe.edu.fineflow.profile.infrastructure.adapter.out.persistence.repository;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import pe.edu.fineflow.profile.infrastructure.adapter.out.persistence.entity.StudentEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface StudentR2dbcRepository extends R2dbcRepository<StudentEntity, String> {
    Mono<StudentEntity> findByIdAndSchoolId(String id, String schoolId);
    Flux<StudentEntity> findAllBySchoolId(String schoolId);
    Flux<StudentEntity> findAllBySectionIdAndSchoolId(String sectionId, String schoolId);
    Mono<Boolean> existsByDocumentNumberAndSchoolId(String documentNumber, String schoolId);
    Mono<Void> deleteByIdAndSchoolId(String id, String schoolId);

    @Query("SELECT * FROM STUDENTS WHERE school_id = :schoolId AND " +
           "(LOWER(first_name || LAST_NAME) LIKE LOWER('%' || :query || '%') " +
           "OR document_number LIKE '%' || :query || '%')")
    Flux<StudentEntity> searchBySchoolId(String schoolId, String query);
}
