package ec.com.airsofka.database.airsofka;

import ec.com.airsofka.data.SeatEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface SeatMongoRepository extends ReactiveMongoRepository<SeatEntity, String> {
    Flux<SeatEntity> findAllByIdFlight(String idFlight);
}
