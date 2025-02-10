package ec.com.airsofka.aggregate.planeManagement.events;

import ec.com.airsofka.generics.domain.DomainEvent;

import java.time.LocalDateTime;

public class MaintenanceCreated extends DomainEvent {
    private String id;
    private LocalDateTime start;
    private LocalDateTime end;
    private String idPlane;

    public MaintenanceCreated(String id, LocalDateTime start, LocalDateTime end, String idPlane) {
        super(EventsPlaneManagementEnum.MAINTENANCE_CREATED.name());
        this.id = id;
        this.start = start;
        this.end = end;
        this.idPlane = idPlane;
    }

    public MaintenanceCreated() {
        super(EventsPlaneManagementEnum.MAINTENANCE_CREATED.name());
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public String getIdPlane() {
        return idPlane;
    }
}
