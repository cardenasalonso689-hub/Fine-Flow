package pe.edu.fineflow.common.event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/** Bus de eventos reactivo en memoria. En producción reemplazar por KafkaTemplate. */
@Component
public class EventBus {
    private static final Logger log = LoggerFactory.getLogger(EventBus.class);
    private final Sinks.Many<DomainEvent> sink = Sinks.many().multicast().onBackpressureBuffer();
    private final Flux<DomainEvent> flux = sink.asFlux().publish().autoConnect();
    public void publish(DomainEvent event) {
        log.debug("EventBus type={} school={}", event.getEventType(), event.getSchoolId());
        sink.tryEmitNext(event);
    }
    @SuppressWarnings("unchecked")
    public <T extends DomainEvent> Flux<T> stream(Class<T> eventType) {
        return (Flux<T>) flux.filter(eventType::isInstance);
    }
}
