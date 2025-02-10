package ec.com.airsofka.booking.queries.usecases;

import ec.com.airsofka.booking.queries.query.GetAmountsQuery;
import ec.com.airsofka.booking.queries.responses.CostBreakdownResponse;
import ec.com.airsofka.generics.interfaces.IUseCaseGet;
import ec.com.airsofka.generics.utils.QueryResponse;
import ec.com.airsofka.seat.queries.usecases.GetPriceBySeatIdViewUseCase;
import ec.com.airsofka.user.queries.usecases.GetFrequentUserViewUseCase;
import org.springframework.security.core.parameters.P;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public class GetCostBreakdownUseCase implements IUseCaseGet<GetAmountsQuery, CostBreakdownResponse> {

    private final GetFrequentUserViewUseCase getFrequentUserViewUseCase;
    private final GetPriceBySeatIdViewUseCase getPriceBySeatIdViewUseCase;

    public GetCostBreakdownUseCase(GetFrequentUserViewUseCase getFrequentUserViewUseCase, GetPriceBySeatIdViewUseCase getPriceBySeatIdViewUseCase) {
        this.getFrequentUserViewUseCase = getFrequentUserViewUseCase;
        this.getPriceBySeatIdViewUseCase = getPriceBySeatIdViewUseCase;
    }

    private static final BigDecimal AIRPORT_TAX = BigDecimal.valueOf(10.00);
    private static final BigDecimal BOOKING_FEE = BigDecimal.valueOf(15.00);
    private static final BigDecimal FUEL_INSURANCE = BigDecimal.valueOf(18.00);
    private static final BigDecimal ADDITIONAL_CHARGES = BigDecimal.valueOf(25.00);



    @Override
    public Mono<QueryResponse<CostBreakdownResponse>> get(GetAmountsQuery request) {
        return Flux.fromIterable(request.getPassengers())
                .flatMap(passenger -> getPriceBySeatIdViewUseCase.get(passenger.getSeatId()))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .flatMap(seatTotal -> {
                    BigDecimal total = seatTotal.add(ADDITIONAL_CHARGES)
                            .add(FUEL_INSURANCE)
                            .add(AIRPORT_TAX)
                            .add(BOOKING_FEE);

                    Mono<BigDecimal> discountAmountMono = (request.getUserId() != null && !request.getUserId().isEmpty()) ?
                            getFrequentUserViewUseCase.get(request.getUserId())
                                    .map(isFrequent -> isFrequent ? total.multiply(BigDecimal.valueOf(0.10)) : BigDecimal.ZERO) :
                            Mono.just(BigDecimal.ZERO);

                    return discountAmountMono.map(discountAmount -> {
                        BigDecimal finalAmount = total.subtract(discountAmount);

                        return QueryResponse.ofSingle(new CostBreakdownResponse(seatTotal,
                                AIRPORT_TAX,
                                finalAmount,
                                BOOKING_FEE,
                                FUEL_INSURANCE,
                                ADDITIONAL_CHARGES,
                                discountAmount));
                    });
                });


    }
}
