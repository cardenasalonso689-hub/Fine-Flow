package pe.edu.fineflow.innovation.application.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pe.edu.fineflow.common.tenant.TenantContext;
import pe.edu.fineflow.common.util.UuidGenerator;
import pe.edu.fineflow.innovation.application.port.in.AiChatUseCase;
import pe.edu.fineflow.innovation.domain.model.ChatMessage;
import pe.edu.fineflow.innovation.domain.model.ChatSession;
import pe.edu.fineflow.innovation.domain.port.out.ChatSessionRepositoryPort;
import reactor.core.publisher.Mono;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * Servicio de chat IA con RAG sobre documentos MINEDU.
 * Llama al chatbot microservice (port 8086) que usa ChromaDB + Anthropic Claude.
 */
@Service
public class AiChatService implements AiChatUseCase {

    private final ChatSessionRepositoryPort repo;
    private final WebClient chatbotClient;

    public AiChatService(ChatSessionRepositoryPort repo,
                          @Value("${fineflow.chatbot.url:http://chatbot-service:8086}") String chatbotUrl) {
        this.repo           = repo;
        this.chatbotClient  = WebClient.builder().baseUrl(chatbotUrl).build();
    }

    @Override
    public Mono<ChatSession> startSession() {
        return TenantContext.getPrincipal().flatMap(p -> {
            ChatSession session = new ChatSession();
            session.setId(UuidGenerator.generate());
            session.setSchoolId(p.schoolId());
            session.setUserId(p.userId());
            session.setUserRole(p.role());
            session.setActive(true);
            session.setStartedAt(Instant.now());
            return repo.save(session);
        });
    }

    @Override
    public Mono<ChatMessage> sendMessage(String sessionId, String userMessage) {
        return TenantContext.getSchoolId().flatMap(schoolId ->
            repo.findByIdAndSchoolId(sessionId, schoolId).flatMap(session -> {
                // Save user message
                ChatMessage userMsg = buildMessage(sessionId, "user", userMessage, null, null);
                return repo.saveMessage(userMsg).then(
                    // Call chatbot RAG service
                    chatbotClient.post().uri("/api/chat/message")
                            .bodyValue(Map.of("message", userMessage, "sessionId", sessionId))
                            .retrieve()
                            .bodyToMono(Map.class)
                            .flatMap(response -> {
                                String answer  = (String) response.get("response");
                                Object confObj = response.get("confidence");
                                BigDecimal conf = confObj != null ?
                                        new BigDecimal(confObj.toString()) : null;
                                ChatMessage assistantMsg = buildMessage(sessionId, "assistant", answer, null, conf);
                                return repo.saveMessage(assistantMsg);
                            })
                            // Fallback MINEDU si el chatbot no responde
                            .onErrorResume(e -> {
                                String fallback = "Lo siento, el servicio de IA no está disponible. " +
                                        "Puedes consultar el Currículo Nacional en minedu.gob.pe";
                                return repo.saveMessage(buildMessage(sessionId, "assistant", fallback, null, BigDecimal.ZERO));
                            })
                );
            })
        );
    }

    @Override
    public Mono<List<ChatMessage>> getHistory(String sessionId) {
        return repo.findMessagesBySessionId(sessionId).collectList();
    }

    @Override
    public Mono<Void> endSession(String sessionId) {
        return repo.closeSession(sessionId);
    }

    private ChatMessage buildMessage(String sessionId, String role, String content,
                                     String sourcesJson, BigDecimal confidence) {
        ChatMessage m = new ChatMessage();
        m.setId(UuidGenerator.generate());
        m.setSessionId(sessionId);
        m.setRole(role);
        m.setContent(content);
        m.setSourcesJson(sourcesJson);
        m.setConfidence(confidence);
        m.setCreatedAt(Instant.now());
        return m;
    }
}
