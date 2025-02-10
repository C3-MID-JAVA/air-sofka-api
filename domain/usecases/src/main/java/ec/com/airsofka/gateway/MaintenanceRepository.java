package ec.com.airsofka.gateway;

import ec.com.airsofka.gateway.dto.MaintenanceDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MaintenanceRepository {
    Mono<MaintenanceDTO> save(MaintenanceDTO maintenanceDTO);
    Flux<MaintenanceDTO> findByStartNow();
    Flux<MaintenanceDTO> findByEnd();
}
