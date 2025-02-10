package ec.com.airsofka.handler;

import ec.com.airsofka.generics.utils.QueryResponse;
import ec.com.airsofka.plane.commands.CreatePlaneCommand;
import ec.com.airsofka.plane.commands.usecases.CreatePlaneUseCase;
import ec.com.airsofka.plane.queries.usecases.GetAllPlaneVIewUseCase;
import ec.com.airsofka.validator.RequestValidatorShared;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class PlaneHandler {
    private final RequestValidatorShared requestValidator;
    private final CreatePlaneUseCase createPlaneUseCase;
    private final GetAllPlaneVIewUseCase getAllPlaneVIewUseCase;

    public PlaneHandler(RequestValidatorShared requestValidator, CreatePlaneUseCase createPlaneUseCase, GetAllPlaneVIewUseCase getAllPlaneVIewUseCase) {
        this.requestValidator = requestValidator;
        this.createPlaneUseCase = createPlaneUseCase;
        this.getAllPlaneVIewUseCase = getAllPlaneVIewUseCase;
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(CreatePlaneCommand.class)
                .flatMap(createPlaneUseCase::execute)
                .flatMap(planeResponse ->
                        ServerResponse
                                .status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(planeResponse)
                );
    }

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return getAllPlaneVIewUseCase.get()
                .map(QueryResponse::getMultipleResults)
                .flatMap(planeResponses ->
                        ServerResponse
                                .ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(planeResponses)
                );
    }
}
