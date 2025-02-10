package ec.com.airsofka.database.airsofka;

import ec.com.airsofka.data.FlightEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface FlightMongoRepository extends ReactiveMongoRepository<FlightEntity, String> {
}
