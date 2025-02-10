package ec.com.airsofka.adapter;

import ec.com.airsofka.data.MaintenanceEntity;
import ec.com.airsofka.database.airsofka.MaintenanceMongoRepository;
import ec.com.airsofka.gateway.MaintenanceRepository;
import ec.com.airsofka.gateway.dto.MaintenanceDTO;
import ec.com.airsofka.mapper.MaintenanceMapperEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Repository
public class MaintenanceMongoAdapter implements MaintenanceRepository {

    private final MaintenanceMongoRepository repository;
    private final ReactiveMongoTemplate airMongoTemplate;

    public MaintenanceMongoAdapter(MaintenanceMongoRepository repository, @Qualifier("airMongoTemplate") ReactiveMongoTemplate airMongoTemplate) {
        this.repository = repository;
        this.airMongoTemplate = airMongoTemplate;
    }

    @Override
    public Mono<MaintenanceDTO> save(MaintenanceDTO maintenanceDTO) {
        MaintenanceEntity maintenanceEntity = MaintenanceMapperEntity.toEntity(maintenanceDTO);

        return repository.save(maintenanceEntity).map(MaintenanceMapperEntity::fromEntity);
    }

    @Override
    public Flux<MaintenanceDTO> findByStartNow() {
        return repository.findByStartLessThanEqualAndEndGreaterThanEqual(LocalDateTime.now()).map(MaintenanceMapperEntity::fromEntity);
    }

    @Override
    public Flux<MaintenanceDTO> findByEnd() {
        return repository.findByEndLessThan(LocalDateTime.now())
                .map(MaintenanceMapperEntity::fromEntity);
    }
}
