package pe.edu.fineflow.report.infrastructure.adapter.out.persistence.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Table("REPORT_JOBS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportJobEntity {
    @Id
    private String id;
    @Column("SCHOOL_ID")       private String schoolId;
    @Column("REQUESTED_BY")     private String requestedBy;
    @Column("REPORT_TYPE")      private String reportType;
    @Column("FORMAT")          private String format;
    @Column("PARAMETERS_JSON") private String parametersJson;
    @Column("STATUS")          private String status;
    @Column("FILE_PATH")       private String filePath;
    @Column("ERROR_DETAIL")    private String errorDetail;
    @Column("FILE_SIZE_KB")    private Long fileSizeKb;
    @Column("PROGRESS_PCT")   private int progressPct;
    @Column("REQUESTED_AT")    private Instant requestedAt;
    @Column("STARTED_AT")      private Instant startedAt;
    @Column("COMPLETED_AT")    private Instant completedAt;
    @Column("EXPIRES_AT")     private Instant expiresAt;
    @Column("DOWNLOAD_COUNT")  private int downloadCount;
    @CreatedDate          @Column("CREATED_AT") private Instant createdAt;
    @LastModifiedDate     @Column("UPDATED_AT") private Instant updatedAt;
}
