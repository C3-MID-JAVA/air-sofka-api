package ec.com.airsofka.generics.interfaces;

import ec.com.airsofka.generics.utils.QueryResponse;
import reactor.core.publisher.Mono;

public interface IUseCaseEmptyGet<R> {
    Mono<QueryResponse<R>> get();
}
