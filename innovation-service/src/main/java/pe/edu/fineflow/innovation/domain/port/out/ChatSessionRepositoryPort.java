package pe.edu.fineflow.innovation.domain.port.out;
import pe.edu.fineflow.innovation.domain.model.ChatSession;
import pe.edu.fineflow.innovation.domain.model.ChatMessage;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
public interface ChatSessionRepositoryPort {
    Mono<ChatSession> save(ChatSession session);
    Mono<ChatSession> findByIdAndSchoolId(String id, String schoolId);
    Mono<ChatMessage> saveMessage(ChatMessage message);
    Flux<ChatMessage> findMessagesBySessionId(String sessionId);
    Mono<Void>        closeSession(String sessionId);
}
