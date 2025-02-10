package ec.com.airsofka.gateway;

import ec.com.airsofka.gateway.dto.PassengerDTO;
import reactor.core.publisher.Mono;

public interface IPassengerRepository {
    Mono<PassengerDTO> save(PassengerDTO passengerDTO);
}
