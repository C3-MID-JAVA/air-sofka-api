package ec.com.airsofka.adapter;

import ec.com.airsofka.database.airsofka.IBillingMongoRepository;
import ec.com.airsofka.gateway.IBillingRepository;
import ec.com.airsofka.gateway.dto.BillingDTO;
import ec.com.airsofka.mapper.BillingMapperEntity;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class BillingMongoAdapter implements IBillingRepository {
    private final IBillingMongoRepository billingMongoRepository;
    private final ReactiveMongoTemplate airMongoTemplate;

    public BillingMongoAdapter(IBillingMongoRepository billingMongoRepository, ReactiveMongoTemplate airMongoTemplate) {
        this.billingMongoRepository = billingMongoRepository;
        this.airMongoTemplate = airMongoTemplate;
    }

    @Override
    public Mono<BillingDTO> save(BillingDTO billingDTO) {

        return billingMongoRepository.save(BillingMapperEntity.toBillingEntity(billingDTO))
                .map(BillingMapperEntity::toBillingDTO);
    }
}
