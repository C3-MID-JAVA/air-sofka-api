package ec.com.airsofka.database.airsofka;

import ec.com.airsofka.data.BillingEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IBillingMongoRepository  extends ReactiveMongoRepository<BillingEntity, String> {
}
