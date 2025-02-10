package ec.com.airsofka.plane.commands.usecases;

import ec.com.airsofka.aggregate.planeManagement.PlaneManagement;
import ec.com.airsofka.gateway.BusEvent;
import ec.com.airsofka.gateway.IEventStore;
import ec.com.airsofka.generics.interfaces.IUseCaseExecute;
import ec.com.airsofka.plane.Plane;
import ec.com.airsofka.plane.PlaneStatus;
import ec.com.airsofka.plane.commands.CreatePlaneCommand;
import ec.com.airsofka.plane.queries.responses.PlaneResponse;
import reactor.core.publisher.Mono;

public class CreatePlaneUseCase implements IUseCaseExecute<CreatePlaneCommand, PlaneResponse> {
    private final IEventStore repository;
    private final BusEvent busEvent;

    public CreatePlaneUseCase(IEventStore repository, BusEvent busEvent) {
        this.repository = repository;
        this.busEvent = busEvent;
    }

    @Override
    public Mono<PlaneResponse> execute(CreatePlaneCommand cmd) {
        PlaneManagement planeManagement = new PlaneManagement();

        planeManagement.createPlane(PlaneStatus.ENABLED, cmd.getModel());

        planeManagement.getUncommittedEvents()
                .stream()
                .map(repository::save)
                .forEach(busEvent::sendEventPlaneCreated);

        planeManagement.getUncommittedEvents();

        Plane newPlane = planeManagement.getPlane();
        return Mono.just(new PlaneResponse(
                newPlane.getId().getValue(),
                newPlane.getState().getValue(),
                newPlane.getModel().getValue()
        ));
    }
}
