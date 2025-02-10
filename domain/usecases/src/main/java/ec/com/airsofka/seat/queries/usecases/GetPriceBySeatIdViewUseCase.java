package ec.com.airsofka.seat.queries.usecases;

import ec.com.airsofka.gateway.SeatRepository;
import ec.com.airsofka.gateway.dto.SeatDTO;
import ec.com.airsofka.generics.interfaces.IUseCaseGetBy;
import ec.com.airsofka.seat.queries.responses.SeatResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public class GetPriceBySeatIdViewUseCase {
    private final SeatRepository seatRepository;

    public GetPriceBySeatIdViewUseCase(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public Mono<BigDecimal> get(String id) {
        return seatRepository.getById(id)
                .map(SeatDTO::getPrice);
    }
}
