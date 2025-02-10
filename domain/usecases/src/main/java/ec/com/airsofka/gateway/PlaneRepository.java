package ec.com.airsofka.gateway;

import ec.com.airsofka.gateway.dto.PlaneDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlaneRepository {
    Mono<PlaneDTO> save(PlaneDTO planeDTO);
    Flux<PlaneDTO> getAll();
    Mono<PlaneDTO> getById(String id);
}
