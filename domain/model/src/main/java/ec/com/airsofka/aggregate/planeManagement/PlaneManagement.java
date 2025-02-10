package ec.com.airsofka.aggregate.planeManagement;


import ec.com.airsofka.aggregate.planeManagement.events.MaintenanceCreated;
import ec.com.airsofka.aggregate.planeManagement.events.PlaneCreated;
import ec.com.airsofka.aggregate.planeManagement.events.PlaneUpdated;
import ec.com.airsofka.aggregate.planeManagement.values.PlaneManagementId;
import ec.com.airsofka.generics.domain.DomainEvent;
import ec.com.airsofka.generics.utils.AggregateRoot;
import ec.com.airsofka.maintenance.Maintenance;
import ec.com.airsofka.maintenance.values.MaintenanceId;
import ec.com.airsofka.plane.Plane;
import ec.com.airsofka.plane.PlaneStatus;
import ec.com.airsofka.plane.values.PlaneId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class PlaneManagement extends AggregateRoot<PlaneManagementId> {
    private Plane plane;
    private Maintenance maintenance;

    public PlaneManagement() {
        super(new PlaneManagementId());
        setSubscription(new PlaneManagementHandler(this));
    }

    public PlaneManagement(final String id) {
        super(PlaneManagementId.of(id));
        setSubscription(new PlaneManagementHandler(this));
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public Maintenance getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(Maintenance maintenance) {
        this.maintenance = maintenance;
    }

    public void createPlane(PlaneStatus state, String model) {
        addEvent(new PlaneCreated(new PlaneId().getValue(), state, model)).apply();
    }

    public void createMaintenance(LocalDateTime start, LocalDateTime end, String idPlane) {
        addEvent(new MaintenanceCreated(new MaintenanceId().getValue(), start, end, idPlane)).apply();
    }

    public void updatePlane(String id, PlaneStatus state, String model) {
        addEvent(new PlaneUpdated(id, state, model)).apply();
    }

    public static Mono<PlaneManagement> from(final String id, Flux<DomainEvent> events) {
        PlaneManagement planeManagement = new PlaneManagement(id);

        return events
                .filter(eventsFilter -> id.equals(eventsFilter.getAggregateRootId()))
                .flatMap(event -> Mono.fromRunnable(() -> planeManagement.addEvent(event).apply()))
                .doOnTerminate(planeManagement::markEventsAsCommitted)
                .then(Mono.just(planeManagement));
    }
}
