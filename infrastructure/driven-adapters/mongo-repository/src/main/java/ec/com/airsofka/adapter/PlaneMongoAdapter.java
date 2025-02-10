package ec.com.airsofka.adapter;

import ec.com.airsofka.data.PlaneEntity;
import ec.com.airsofka.database.airsofka.PlaneMongoRepository;
import ec.com.airsofka.gateway.PlaneRepository;
import ec.com.airsofka.gateway.dto.PlaneDTO;
import ec.com.airsofka.mapper.PlaneMapperEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class PlaneMongoAdapter implements PlaneRepository {

    private final PlaneMongoRepository repository;
    private final ReactiveMongoTemplate airMongoTemplate;

    public PlaneMongoAdapter(PlaneMongoRepository repository, @Qualifier("airMongoTemplate") ReactiveMongoTemplate airMongoTemplate) {
        this.repository = repository;
        this.airMongoTemplate = airMongoTemplate;
    }

    @Override
    public Mono<PlaneDTO> save(PlaneDTO planeDTO) {
        PlaneEntity planeEntity = PlaneMapperEntity.toEntity(planeDTO);
        return repository.save(planeEntity).map(PlaneMapperEntity::fromEntity);
    }

    @Override
    public Flux<PlaneDTO> getAll() {
        return repository.findAll().map(PlaneMapperEntity::fromEntity);
    }

    @Override
    public Mono<PlaneDTO> getById(String id) {
        return repository.findById(id).map(PlaneMapperEntity::fromEntity);
    }
}
