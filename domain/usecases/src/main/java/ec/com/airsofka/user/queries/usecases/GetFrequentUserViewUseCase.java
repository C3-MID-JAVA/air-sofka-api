package ec.com.airsofka.user.queries.usecases;

import ec.com.airsofka.gateway.IUserRepository;
import ec.com.airsofka.gateway.dto.UserDTO;
import reactor.core.publisher.Mono;

public class GetFrequentUserViewUseCase {
    private final IUserRepository repository;

    public GetFrequentUserViewUseCase(IUserRepository repository) {
        this.repository = repository;
    }

    public Mono<Boolean> get(String id) {
        return repository.findById(id)
                .map(UserDTO::getFrequent)
                .switchIfEmpty(Mono.just(false));
    }
}
