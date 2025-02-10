package ec.com.airsofka.database.airsofka;

import ec.com.airsofka.data.MaintenanceEntity;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

public interface MaintenanceMongoRepository extends ReactiveMongoRepository<MaintenanceEntity, String> {
    @Query("{'start': { $lte: ?0 }, 'end': { $gte: ?0 }}")
    Flux<MaintenanceEntity> findByStartLessThanEqualAndEndGreaterThanEqual(LocalDateTime now);
    Flux<MaintenanceEntity> findByEndLessThan(LocalDateTime now);
}
