package ec.com.airsofka.database.airsofka;

import ec.com.airsofka.data.PlaneEntity;
import ec.com.airsofka.data.UserEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface PlaneMongoRepository extends ReactiveMongoRepository<PlaneEntity, String> {
}
