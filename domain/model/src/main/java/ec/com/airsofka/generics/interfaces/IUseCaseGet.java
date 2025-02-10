package ec.com.airsofka.generics.interfaces;

import ec.com.airsofka.generics.utils.Query;
import ec.com.airsofka.generics.utils.QueryResponse;
import reactor.core.publisher.Mono;

public interface IUseCaseGet <Q extends Query, R> {
    Mono<QueryResponse<R>> get(Q request);
}
