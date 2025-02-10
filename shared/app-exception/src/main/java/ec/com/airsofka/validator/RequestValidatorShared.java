package ec.com.airsofka.validator;

import ec.com.airsofka.exceptions.RequestValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

@Component
public class RequestValidatorShared {
    private final Validator validator;

    public RequestValidatorShared(Validator validator) {
        this.validator = validator;
    }

    public <T> Mono<T> validate(T request) {
        Set<ConstraintViolation<T>> violations = validator.validate(request);

        if (!violations.isEmpty()) {
            List<String> errors = violations.stream()
                    .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                    .toList();
            return Mono.error(new RequestValidationException(errors));
        }

        return Mono.just(request);
    }

}