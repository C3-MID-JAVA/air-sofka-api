package ec.com.airsofka.adapter;

import ec.com.airsofka.database.airsofka.IPassengerMongoRepository;
import ec.com.airsofka.gateway.IPassengerRepository;
import ec.com.airsofka.gateway.dto.PassengerDTO;
import ec.com.airsofka.mapper.PassengerMapperEntity;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class PassengerMongoAdapter implements IPassengerRepository {
    private final ReactiveMongoTemplate airMongoTemplate;
    private final IPassengerMongoRepository repository;

    public PassengerMongoAdapter(ReactiveMongoTemplate airMongoTemplate, IPassengerMongoRepository repository) {
        this.airMongoTemplate = airMongoTemplate;
        this.repository = repository;
    }


    @Override
    public Mono<PassengerDTO> save(PassengerDTO passengerDTO) {
        return repository.save(PassengerMapperEntity.toPassengerEntity(passengerDTO))
                .map(PassengerMapperEntity::toPassengerDTO);
    }
}
