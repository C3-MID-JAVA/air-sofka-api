package ec.com.airsofka.aggregate.planeManagement.events;

import ec.com.airsofka.generics.domain.DomainEvent;
import ec.com.airsofka.plane.PlaneStatus;

public class PlaneUpdated extends DomainEvent {
    private String id;
    private PlaneStatus state;
    private String model;

    public PlaneUpdated() {
        super(EventsPlaneManagementEnum.PLANE_UPDATED.name());
    }

    public PlaneUpdated(String id, PlaneStatus state, String model) {
        super(EventsPlaneManagementEnum.PLANE_UPDATED.name());
        this.id = id;
        this.state = state;
        this.model = model;
    }

    public String getId() {
        return id;
    }

    public PlaneStatus getState() {
        return state;
    }

    public String getModel() {
        return model;
    }
}
