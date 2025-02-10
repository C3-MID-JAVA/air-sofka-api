package ec.com.airsofka.aggregate.planeManagement;

import ec.com.airsofka.aggregate.planeManagement.events.MaintenanceCreated;
import ec.com.airsofka.aggregate.planeManagement.events.PlaneCreated;
import ec.com.airsofka.aggregate.planeManagement.events.PlaneUpdated;
import ec.com.airsofka.generics.domain.DomainActionsContainer;
import ec.com.airsofka.maintenance.Maintenance;
import ec.com.airsofka.maintenance.values.MaintenanceId;
import ec.com.airsofka.maintenance.values.objects.End;
import ec.com.airsofka.maintenance.values.objects.Start;
import ec.com.airsofka.plane.Plane;
import ec.com.airsofka.plane.values.PlaneId;
import ec.com.airsofka.plane.values.objects.Model;
import ec.com.airsofka.plane.values.objects.State;

public class PlaneManagementHandler extends DomainActionsContainer {
    public PlaneManagementHandler(PlaneManagement planeManagement) {
        addDomainActions((PlaneCreated event) -> {
            Plane plane = new Plane(
                    PlaneId.of(event.getId()),
                    State.of(event.getState()),
                    Model.of(event.getModel())
            );
            planeManagement.setPlane(plane);
        });

        addDomainActions((MaintenanceCreated event) -> {
            Maintenance maintenance = new Maintenance(
                    MaintenanceId.of(event.getId()),
                    Start.of(event.getStart()),
                    End.of(event.getEnd()),
                    PlaneId.of(event.getIdPlane())
            );
            planeManagement.setMaintenance(maintenance);
        });

        addDomainActions((PlaneUpdated event) -> {
            Plane plane = new Plane(
                    PlaneId.of(event.getId()),
                    State.of(event.getState()),
                    Model.of(event.getModel())
            );
            planeManagement.setPlane(plane);
        });
    }
}
