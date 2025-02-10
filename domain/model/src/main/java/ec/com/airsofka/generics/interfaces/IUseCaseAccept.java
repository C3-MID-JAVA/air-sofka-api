package ec.com.airsofka.generics.interfaces;

public interface IUseCaseAccept <T, Void>{
    void accept(T request);
}
