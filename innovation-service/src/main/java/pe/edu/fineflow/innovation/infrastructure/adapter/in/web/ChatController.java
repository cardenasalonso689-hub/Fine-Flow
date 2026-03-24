package pe.edu.fineflow.innovation.infrastructure.adapter.in.web;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.fineflow.innovation.application.port.in.AiChatUseCase;
import pe.edu.fineflow.innovation.domain.model.ChatMessage;
import pe.edu.fineflow.innovation.domain.model.ChatSession;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/innovation/chat")
@Tag(name = "Chat IA MINEDU", description = "Asistente de IA con RAG sobre el Currículo Nacional")
public class ChatController {

    private final AiChatUseCase useCase;
    public ChatController(AiChatUseCase useCase) { this.useCase = useCase; }

    @PostMapping("/sessions")
    @PreAuthorize("isAuthenticated()")
    public Mono<ChatSession> startSession() { return useCase.startSession(); }

    @PostMapping("/sessions/{sessionId}/messages")
    @PreAuthorize("isAuthenticated()")
    public Mono<ChatMessage> sendMessage(@PathVariable String sessionId,
                                          @RequestBody Map<String, String> body) {
        return useCase.sendMessage(sessionId, body.get("message"));
    }

    @GetMapping("/sessions/{sessionId}/messages")
    @PreAuthorize("isAuthenticated()")
    public Mono<List<ChatMessage>> getHistory(@PathVariable String sessionId) {
        return useCase.getHistory(sessionId);
    }

    @DeleteMapping("/sessions/{sessionId}")
    @PreAuthorize("isAuthenticated()")
    public Mono<Void> endSession(@PathVariable String sessionId) {
        return useCase.endSession(sessionId);
    }
}
