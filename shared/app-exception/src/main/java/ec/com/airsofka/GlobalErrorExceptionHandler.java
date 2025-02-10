package ec.com.airsofka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ec.com.airsofka.exceptions.*;
import ec.com.airsofka.exceptions.model.ErrorDetails;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class GlobalErrorExceptionHandler implements ErrorWebExceptionHandler {

    private final ObjectMapper objectMapper;

    public GlobalErrorExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    private static final Map<Class<? extends Throwable>, Function<Throwable, ErrorDetails>> exceptionHandlers = Map.ofEntries(
            Map.entry(EmptyCollectionException.class, ex -> new ErrorDetails(HttpStatus.NOT_FOUND.value(), ex.getMessage(), new Date())),
            Map.entry(ConflictException.class, ex -> new ErrorDetails(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), new Date())),
            Map.entry(TransactionRejectedException.class, ex -> new ErrorDetails(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), new Date())),
            Map.entry(RecordNotFoundException.class, ex -> new ErrorDetails(HttpStatus.NOT_FOUND.value(), ex.getMessage(), new Date())),
            Map.entry(InvalidFieldException.class, ex -> new ErrorDetails(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), new Date())),
            Map.entry(ExpiredJwtException.class, ex -> new ErrorDetails(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), new Date())),
            Map.entry(UsernameNotFoundException.class, ex -> new ErrorDetails(HttpStatus.FORBIDDEN.value(), ex.getMessage(), new Date())),
            Map.entry(EmailAlreadyExistsException.class, ex -> new ErrorDetails(HttpStatus.CONFLICT.value(), ex.getMessage(), new Date())),
            Map.entry(RequestValidationException.class, ex -> {
                List<String> errors = ((RequestValidationException) ex).getErrors();
                return new ErrorDetails(HttpStatus.UNPROCESSABLE_ENTITY.value(), String.join(", ", errors), new Date());
            }),
            Map.entry(BadCredentialsException.class, ex -> new ErrorDetails(HttpStatus.FORBIDDEN.value(), ex.getMessage(), new Date())),
            Map.entry(InternalServerException.class, ex -> new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected error occurred.", new Date()))
    );

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        Function<Throwable, ErrorDetails> handler = exceptionHandlers.getOrDefault(ex.getClass(), exceptionHandlers.get(InternalServerException.class));

        ErrorDetails errorResponse = handler.apply(ex);

        exchange.getResponse().setStatusCode(HttpStatus.valueOf(errorResponse.getStatus()));
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        try {
            byte[] errorResponseBytes = objectMapper.writeValueAsBytes(errorResponse);
            return exchange.getResponse()
                    .writeWith(Mono.just(exchange.getResponse()
                            .bufferFactory()
                            .wrap(errorResponseBytes)));
        } catch (JsonProcessingException e) {
            return Mono.error(new InternalServerException("Error processing response"));
        }

    }

}