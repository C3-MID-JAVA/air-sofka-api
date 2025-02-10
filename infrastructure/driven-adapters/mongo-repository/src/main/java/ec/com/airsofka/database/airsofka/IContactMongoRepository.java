package ec.com.airsofka.database.airsofka;

import ec.com.airsofka.data.ContactEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IContactMongoRepository extends ReactiveMongoRepository<ContactEntity, String> {
}
