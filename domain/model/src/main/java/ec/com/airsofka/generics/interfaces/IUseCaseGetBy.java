package ec.com.airsofka.generics.interfaces;

import ec.com.airsofka.generics.utils.QueryResponse;
import org.reactivestreams.Publisher;

public interface IUseCaseGetBy<Q, R>{
    Publisher<QueryResponse<R>> get(Q request);
}
