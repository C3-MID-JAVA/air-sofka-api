package ec.com.airsofka.handler;

import ec.com.airsofka.generics.utils.QueryResponse;
import ec.com.airsofka.seat.commands.UpdateSeatStatusCommand;
import ec.com.airsofka.seat.commands.usecases.UpdateSeatStatusUseCase;
import ec.com.airsofka.seat.queries.usecases.GetSeatsByFlightIdViewUseCase;
import ec.com.airsofka.seat.queries.usecases.SeatListSavedViewUseCase;
import ec.com.airsofka.validator.RequestValidatorShared;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class SeatHandler {
    private final RequestValidatorShared requestValidator;
    private final GetSeatsByFlightIdViewUseCase getSeatsByFlightIdViewUseCase;
    private final UpdateSeatStatusUseCase updateSeatStatusUseCase;

    public SeatHandler(RequestValidatorShared requestValidator, GetSeatsByFlightIdViewUseCase getSeatsByFlightIdViewUseCase, UpdateSeatStatusUseCase updateSeatStatusUseCase, SeatListSavedViewUseCase seatListSavedViewUseCase ) {
        this.requestValidator = requestValidator;
        this.getSeatsByFlightIdViewUseCase = getSeatsByFlightIdViewUseCase;
        this.updateSeatStatusUseCase = updateSeatStatusUseCase;
    }

    public Mono<ServerResponse> update(ServerRequest request) {
       // return request.bodyToMono(SeatRequestDTO.class)
        return request.bodyToMono(UpdateSeatStatusCommand.class)
                //.flatMap(requestValidator::validate)
               // .map(SeatMapper::toCommand)
                .flatMap(updateSeatStatusUseCase::execute)
                .flatMap(seatResponse ->
                        ServerResponse
                                .status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(seatResponse)
                );
    }

    public Mono<ServerResponse> getAllByFlightId(ServerRequest request) {
        String idFlight = request.pathVariable("idFlight");
        return getSeatsByFlightIdViewUseCase.get(idFlight)
                .map(QueryResponse::getMultipleResults)
                .flatMap(seatResponses ->
                        ServerResponse
                                .ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(seatResponses)
                );
    }
}
