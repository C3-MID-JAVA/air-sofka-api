package ec.com.airsofka.database.events;

import ec.com.airsofka.data.EventEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface IEventMongoRepository extends ReactiveMongoRepository<EventEntity, String> {
    Flux<EventEntity> findAllByAggregateId(String aggregateId);
    Flux<EventEntity> findAllByAggregateRootName(String aggregateRootName);
    Flux<EventEntity> findAllByAggregateRootNameAndEventType(String aggregateRootName, String eventType);
    Flux<EventEntity> findByAggregateId(String aggregateId);

}
