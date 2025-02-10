package ec.com.airsofka.database.airsofka;

import ec.com.airsofka.data.PassengerEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IPassengerMongoRepository  extends ReactiveMongoRepository<PassengerEntity, String> {

}
