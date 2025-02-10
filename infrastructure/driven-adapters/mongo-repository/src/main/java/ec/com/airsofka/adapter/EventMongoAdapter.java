package ec.com.airsofka.adapter;

import ec.com.airsofka.JSONMap;
import ec.com.airsofka.data.EventEntity;
import ec.com.airsofka.database.events.IEventMongoRepository;
import ec.com.airsofka.gateway.IEventStore;
import ec.com.airsofka.generics.domain.DomainEvent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;

@Repository
public class EventMongoAdapter implements IEventStore {

    private final IEventMongoRepository repository;
    private final JSONMap mapper;
    private final ReactiveMongoTemplate eventMongoTemplate;

    public EventMongoAdapter(IEventMongoRepository repository, JSONMap mapper, @Qualifier("eventMongoTemplate") ReactiveMongoTemplate eventMongoTemplate) {
        this.repository = repository;
        this.mapper = mapper;
        this.eventMongoTemplate = eventMongoTemplate;
    }

    @Override
    public Mono<DomainEvent> save(DomainEvent event) {
        EventEntity e = new EventEntity(
                event.getEventId(),
                event.getAggregateRootId(),
                event.getEventType(),
                EventEntity.wrapEvent(event, mapper),
                event.getWhen().toString(),
                event.getVersion(),
                event.getAggregateRootName()
        );
        return repository.save(e)
                .thenReturn(event);
    }

    @Override
    public Flux<DomainEvent> getEventsByAggregateId(String aggregateId) {
        return repository.findByAggregateId(aggregateId)
                .map(eventEntity -> eventEntity.deserializeEvent(mapper, aggregateId))
                .sort(Comparator.comparing(DomainEvent::getVersion)
                        .thenComparing(DomainEvent::getWhen));
    }

    @Override
    public Flux<DomainEvent> findAggregate(String aggregateId, String aggregate) {
        return repository.findAllByAggregateId(aggregateId.toLowerCase())
                .map(eventEntity -> eventEntity.deserializeEvent(mapper, aggregate))
                .sort(Comparator.comparing(DomainEvent::getVersion)
                        .thenComparing(DomainEvent::getWhen));
    }

    @Override
    public Flux<DomainEvent> findAllAggregate(String aggregate) {
        return repository.findAllByAggregateRootName(aggregate)
                .map(eventEntity -> eventEntity.deserializeEvent(mapper, aggregate))
                .sort(Comparator.comparing(DomainEvent::getVersion)
                        .thenComparing(DomainEvent::getWhen));
    }

    @Override
    public Flux<DomainEvent> findAllAggregateByEvent(String aggregate, String eventType) {
        return repository.findAllByAggregateRootNameAndEventType(aggregate.toLowerCase(), eventType)
                .map(eventEntity -> eventEntity.deserializeEvent(mapper, aggregate))
                .sort(Comparator.comparing(DomainEvent::getVersion)
                        .thenComparing(DomainEvent::getWhen));
    }

}
