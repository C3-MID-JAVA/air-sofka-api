package ec.com.airsofka.gateway;

import ec.com.airsofka.gateway.dto.SeatDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface SeatRepository {
    Mono<SeatDTO> save(SeatDTO seatDTO);
    Flux<SeatDTO> getAll();
    Flux<SeatDTO> getAllByFlightId(String id);
    Mono<SeatDTO> getById(String id);
}
