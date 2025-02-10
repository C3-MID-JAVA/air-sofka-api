package ec.com.airsofka.generics.interfaces;

import ec.com.airsofka.generics.utils.Request;
import org.reactivestreams.Publisher;

public interface IUseCase<T extends Request, R> {
    Publisher<R> execute(T request);
}
