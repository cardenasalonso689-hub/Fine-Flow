package pe.edu.fineflow.innovation.application.port.in;
import pe.edu.fineflow.innovation.domain.model.ChatMessage;
import pe.edu.fineflow.innovation.domain.model.ChatSession;
import reactor.core.publisher.Mono;
import java.util.List;
public interface AiChatUseCase {
    Mono<ChatSession>       startSession();
    Mono<ChatMessage>       sendMessage(String sessionId, String userMessage);
    Mono<List<ChatMessage>> getHistory(String sessionId);
    Mono<Void>              endSession(String sessionId);
}
