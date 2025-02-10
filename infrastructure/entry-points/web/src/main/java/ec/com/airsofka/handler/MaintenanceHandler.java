package ec.com.airsofka.handler;

import ec.com.airsofka.maintenance.commands.CreateMaintenanceCommand;
import ec.com.airsofka.maintenance.commands.usecases.CreateMaintenanceUseCase;
import ec.com.airsofka.validator.RequestValidatorShared;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class MaintenanceHandler {
    private final RequestValidatorShared requestValidator;
    private final CreateMaintenanceUseCase createMaintenanceUseCase;

    public MaintenanceHandler(RequestValidatorShared requestValidator, CreateMaintenanceUseCase createMaintenanceUseCase) {
        this.requestValidator = requestValidator;
        this.createMaintenanceUseCase = createMaintenanceUseCase;
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(CreateMaintenanceCommand.class)
                .flatMap(createMaintenanceUseCase::execute)
                .flatMap(maintenanceResponse ->
                        ServerResponse
                                .status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(maintenanceResponse)
                );
    }
}
