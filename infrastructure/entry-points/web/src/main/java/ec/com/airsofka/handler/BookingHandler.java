package ec.com.airsofka.handler;


import ec.com.airsofka.booking.commands.CreateBookingCommand;
import ec.com.airsofka.booking.commands.usecases.CreateBookingUseCase;
import ec.com.airsofka.booking.queries.query.GetAmountsQuery;
import ec.com.airsofka.booking.queries.usecases.GetCostBreakdownUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class BookingHandler {
    private final CreateBookingUseCase createBookingUseCase;
    private final GetCostBreakdownUseCase getCostBreakdownUseCase;

    public BookingHandler(CreateBookingUseCase createBookingUseCase, GetCostBreakdownUseCase getCostBreakdownUseCase) {
        this.createBookingUseCase = createBookingUseCase;
        this.getCostBreakdownUseCase = getCostBreakdownUseCase;
    }


    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(CreateBookingCommand.class)
                .flatMap(createBookingUseCase::execute)
                .flatMap(flightResponse ->
                        ServerResponse
                                .status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(flightResponse)
                );
    }

    public Mono<ServerResponse> getCostBreakdown(ServerRequest request) {
        return request.bodyToMono(GetAmountsQuery.class)
                .flatMap(getCostBreakdownUseCase::get)
                .flatMap(queryResponse ->
                        ServerResponse
                                .status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(queryResponse.getSingleResult())
                );
    }

}
