package ec.com.airsofka.gateway;

import ec.com.airsofka.flight.queries.query.GetAllFlightQuery;
import ec.com.airsofka.gateway.dto.FlightDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IFlightRepository {
    Mono<FlightDTO> save(FlightDTO flightDTO);
    Flux<FlightDTO> getAll(GetAllFlightQuery filters);
}
