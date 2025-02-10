package ec.com.airsofka.generics.interfaces;

import ec.com.airsofka.generics.utils.Command;
import org.reactivestreams.Publisher;

public interface IUseCaseExecute<T extends Command, R> {
    Publisher<R> execute(T cmd);
}
