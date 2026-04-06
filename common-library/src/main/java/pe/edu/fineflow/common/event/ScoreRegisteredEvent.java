package pe.edu.fineflow.common.event;

import java.math.BigDecimal;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
public final class ScoreRegisteredEvent extends DomainEvent {
    private final String scoreId;
    private final String studentId;
    private final String classTaskId;
    private final BigDecimal score;

    public ScoreRegisteredEvent(String schoolId, String triggeredBy, String scoreId, 
            String studentId, String classTaskId, BigDecimal score) {
        super(schoolId, triggeredBy);
        this.scoreId = scoreId; 
        this.studentId = studentId; 
        this.classTaskId = classTaskId; 
        this.score = score;
    }

    @Override public String getEventType() { return "SCORE_REGISTERED"; }
}
