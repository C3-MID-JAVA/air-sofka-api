package ec.com.airsofka.seat.queries.usecases;

import ec.com.airsofka.gateway.SeatRepository;
import ec.com.airsofka.generics.interfaces.IUseCaseGetBy;
import ec.com.airsofka.generics.utils.QueryResponse;
import ec.com.airsofka.seat.queries.responses.SeatResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GetSeatsByFlightIdViewUseCase implements IUseCaseGetBy<String, SeatResponse> {
    private final SeatRepository seatRepository;

    public GetSeatsByFlightIdViewUseCase(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public Mono<QueryResponse<SeatResponse>> get(String flightId) {
        return seatRepository.getAllByFlightId(flightId)
                .map(seatDTO -> new SeatResponse(
                        seatDTO.getId(),
                        seatDTO.getNumber(),
                        seatDTO.getRow(),
                        seatDTO.getColumn(),
                        seatDTO.getType(),
                        seatDTO.getStatus(),
                        seatDTO.getPrice(),
                        seatDTO.getIdFlight()
                ))
                .collectList()
                .flatMap(seats -> Mono.just(QueryResponse.ofMultiple(seats)));
    }
}
