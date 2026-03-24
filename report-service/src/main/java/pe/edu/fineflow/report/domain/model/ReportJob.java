package pe.edu.fineflow.report.domain.model;
import java.time.Instant;
public class ReportJob {
    private String id, schoolId, requestedBy, reportType, format;
    private String parametersJson, status, filePath, errorDetail;
    private Long fileSizeKb;
    private int progressPct;
    private Instant requestedAt, startedAt, completedAt, expiresAt;
    private int downloadCount;
    public String getId()          { return id; }
    public String getSchoolId()    { return schoolId; }
    public String getRequestedBy() { return requestedBy; }
    public String getReportType()  { return reportType; }
    public String getFormat()      { return format; }
    public String getStatus()      { return status; }
    public String getFilePath()    { return filePath; }
    public int    getProgressPct() { return progressPct; }
    public void setId(String v)            { this.id = v; }
    public void setSchoolId(String v)      { this.schoolId = v; }
    public void setRequestedBy(String v)   { this.requestedBy = v; }
    public void setReportType(String v)    { this.reportType = v; }
    public void setFormat(String v)        { this.format = v; }
    public void setParametersJson(String v){ this.parametersJson = v; }
    public void setStatus(String v)        { this.status = v; }
    public void setFilePath(String v)      { this.filePath = v; }
    public void setFileSizeKb(Long v)      { this.fileSizeKb = v; }
    public void setProgressPct(int v)      { this.progressPct = v; }
    public void setRequestedAt(Instant v)  { this.requestedAt = v; }
    public void setStartedAt(Instant v)    { this.startedAt = v; }
    public void setCompletedAt(Instant v)  { this.completedAt = v; }
    public void setExpiresAt(Instant v)    { this.expiresAt = v; }
    public void setErrorDetail(String v)   { this.errorDetail = v; }
    public Instant getCompletedAt()        { return completedAt; }
}
