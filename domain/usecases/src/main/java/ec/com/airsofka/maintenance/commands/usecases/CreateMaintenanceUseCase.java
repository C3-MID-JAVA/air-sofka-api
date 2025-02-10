package ec.com.airsofka.maintenance.commands.usecases;

import ec.com.airsofka.aggregate.planeManagement.PlaneManagement;
import ec.com.airsofka.aggregate.planeManagement.events.EventsPlaneManagementEnum;
import ec.com.airsofka.aggregate.planeManagement.events.PlaneCreated;
import ec.com.airsofka.gateway.BusEvent;
import ec.com.airsofka.gateway.IEventStore;
import ec.com.airsofka.generics.domain.DomainEvent;
import ec.com.airsofka.generics.interfaces.IUseCaseExecute;
import ec.com.airsofka.maintenance.Maintenance;
import ec.com.airsofka.maintenance.commands.CreateMaintenanceCommand;
import ec.com.airsofka.maintenance.queries.responses.MaintenanceResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CreateMaintenanceUseCase implements IUseCaseExecute<CreateMaintenanceCommand, MaintenanceResponse> {
    private final IEventStore repository;
    private final BusEvent busEvent;

    public CreateMaintenanceUseCase(IEventStore repository, BusEvent busEvent) {
        this.repository = repository;
        this.busEvent = busEvent;
    }

    @Override
    public Mono<MaintenanceResponse> execute(CreateMaintenanceCommand cmd) {
        Mono<PlaneCreated> planeCreatedEvent = repository.findAllAggregateByEvent("planeManagement", EventsPlaneManagementEnum.PLANE_CREATED.name())
                .map(event -> (PlaneCreated) event)
                .filter(event -> event.getId().equals(cmd.getIdPlane()))
                .single();

        return planeCreatedEvent.flatMap(planeCreated -> {
            Flux<DomainEvent> eventsPlane = repository.findAggregate(planeCreated.getAggregateRootId(), "planeManagement");

            return PlaneManagement.from(planeCreated.getAggregateRootId(), eventsPlane)
                    .flatMap(planeManagement -> {
                        planeManagement.createMaintenance(cmd.getStart(), cmd.getEnd(), cmd.getIdPlane());

                        planeManagement.getUncommittedEvents()
                                .stream()
                                .map(repository::save)
                                .forEach(busEvent::sendEventMaintenanceCreated);
                        planeManagement.markEventsAsCommitted();

                        Maintenance newMaintenance = planeManagement.getMaintenance();
                        return Mono.just(new MaintenanceResponse(
                                newMaintenance.getId().getValue(),
                                newMaintenance.getStart().getValue(),
                                newMaintenance.getEnd().getValue(),
                                newMaintenance.getIdPlane().getValue()
                        ));
                    });
        });
    }
}
