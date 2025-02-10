package ec.com.airsofka.plane.queries.usecases;

import ec.com.airsofka.gateway.PlaneRepository;
import ec.com.airsofka.generics.interfaces.IUseCaseEmptyGet;
import ec.com.airsofka.generics.utils.QueryResponse;
import ec.com.airsofka.plane.queries.responses.PlaneResponse;
import reactor.core.publisher.Mono;

public class GetAllPlaneVIewUseCase implements IUseCaseEmptyGet<PlaneResponse> {
    private final PlaneRepository planeRepository;

    public GetAllPlaneVIewUseCase(PlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
    }

    @Override
    public Mono<QueryResponse<PlaneResponse>> get() {
        return planeRepository.getAll()
                .map(planeDto ->
                        new PlaneResponse(
                                planeDto.getId(),
                                planeDto.getState(),
                                planeDto.getModel()
                        )
                )
                .collectList()
                .flatMap(planes -> Mono.just(QueryResponse.ofMultiple(planes)));
    }
}
