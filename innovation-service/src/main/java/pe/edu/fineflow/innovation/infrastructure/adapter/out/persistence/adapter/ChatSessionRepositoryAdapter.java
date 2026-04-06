package pe.edu.fineflow.innovation.infrastructure.adapter.out.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.edu.fineflow.innovation.domain.model.ChatSession;
import pe.edu.fineflow.innovation.domain.model.ChatMessage;
import pe.edu.fineflow.innovation.domain.port.out.ChatSessionRepositoryPort;
import pe.edu.fineflow.innovation.infrastructure.adapter.out.persistence.entity.ChatSessionEntity;
import pe.edu.fineflow.innovation.infrastructure.adapter.out.persistence.entity.ChatMessageEntity;
import pe.edu.fineflow.innovation.infrastructure.adapter.out.persistence.repository.ChatSessionR2dbcRepository;
import pe.edu.fineflow.innovation.infrastructure.adapter.out.persistence.repository.ChatMessageR2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ChatSessionRepositoryAdapter implements ChatSessionRepositoryPort {

    private final ChatSessionR2dbcRepository sessionRepository;
    private final ChatMessageR2dbcRepository messageRepository;

    @Override
    public Mono<ChatSession> save(ChatSession session) {
        return sessionRepository.save(toSessionEntity(session)).map(this::toSessionModel);
    }

    @Override
    public Mono<ChatSession> findByIdAndSchoolId(String id, String schoolId) {
        return sessionRepository.findByIdAndSchoolId(id, schoolId).map(this::toSessionModel);
    }

    @Override
    public Mono<ChatMessage> saveMessage(ChatMessage message) {
        return messageRepository.save(toMessageEntity(message)).map(this::toMessageModel);
    }

    @Override
    public Flux<ChatMessage> findMessagesBySessionId(String sessionId) {
        return messageRepository.findBySessionIdOrderByCreatedAtAsc(sessionId).map(this::toMessageModel);
    }

    @Override
    public Mono<Void> closeSession(String sessionId) {
        return sessionRepository.findById(sessionId)
            .flatMap(s -> {
                s.setActive(false);
                s.setEndedAt(java.time.Instant.now());
                return sessionRepository.save(s);
            })
            .then();
    }

    private ChatSessionEntity toSessionEntity(ChatSession m) {
        ChatSessionEntity e = new ChatSessionEntity();
        e.setId(m.getId());
        e.setSchoolId(m.getSchoolId());
        e.setUserId(m.getUserId());
        e.setUserRole(m.getUserRole());
        e.setSessionToken(m.getSessionToken());
        e.setStartedAt(m.getStartedAt());
        e.setLastMessageAt(m.getLastMessageAt());
        e.setEndedAt(m.getEndedAt());
        e.setActive(m.isActive());
        return e;
    }

    private ChatSession toSessionModel(ChatSessionEntity e) {
        return new ChatSession(
            e.getId(), e.getSchoolId(), e.getUserId(), e.getUserRole(),
            e.getSessionToken(), e.getStartedAt(), e.getLastMessageAt(), e.getEndedAt(), e.isActive()
        );
    }

    private ChatMessageEntity toMessageEntity(ChatMessage m) {
        ChatMessageEntity e = new ChatMessageEntity();
        e.setId(m.getId());
        e.setSessionId(m.getSessionId());
        e.setRole(m.getRole());
        e.setContent(m.getContent());
        e.setSourcesJson(m.getSourcesJson());
        e.setConfidence(m.getConfidence());
        e.setCreatedAt(m.getCreatedAt());
        return e;
    }

    private ChatMessage toMessageModel(ChatMessageEntity e) {
        return new ChatMessage(
            e.getId(), e.getSessionId(), e.getRole(), e.getContent(),
            e.getSourcesJson(), e.getConfidence(), e.getCreatedAt()
        );
    }
}
