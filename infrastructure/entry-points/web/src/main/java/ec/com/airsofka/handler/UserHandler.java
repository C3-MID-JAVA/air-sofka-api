package ec.com.airsofka.handler;

import ec.com.airsofka.user.commands.CreateUserCommand;
import ec.com.airsofka.user.commands.usecases.CreateUserUsecase;
import ec.com.airsofka.exceptions.EmailAlreadyExistsException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {

    private final CreateUserUsecase createUserUsecase;

    public UserHandler(CreateUserUsecase createUserUsecase) {
        this.createUserUsecase = createUserUsecase;
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(CreateUserCommand.class)
                .flatMap(createUserUsecase::execute)
                .flatMap(user -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(user))
                .onErrorResume(EmailAlreadyExistsException.class, e ->
                        ServerResponse.badRequest()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(new ErrorResponse(e.getMessage()))
                );
    }

    public class ErrorResponse {
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        // Getters y setters
        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }


}


