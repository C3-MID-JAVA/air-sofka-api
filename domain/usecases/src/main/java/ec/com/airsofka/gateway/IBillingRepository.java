package ec.com.airsofka.gateway;

import ec.com.airsofka.gateway.dto.BillingDTO;
import reactor.core.publisher.Mono;

public interface IBillingRepository {
    Mono<BillingDTO> save(BillingDTO billingDTO);
}
