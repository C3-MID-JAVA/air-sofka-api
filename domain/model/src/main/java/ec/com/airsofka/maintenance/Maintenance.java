package ec.com.airsofka.maintenance;

import ec.com.airsofka.generics.utils.Entity;
import ec.com.airsofka.maintenance.values.MaintenanceId;
import ec.com.airsofka.maintenance.values.objects.End;
import ec.com.airsofka.maintenance.values.objects.Start;
import ec.com.airsofka.plane.values.PlaneId;

public class Maintenance extends Entity<MaintenanceId> {
    private final Start start;
    private final End end;
    private final PlaneId idPlane;

    public Maintenance(MaintenanceId id, Start start, End end, PlaneId idPlane) {
        super(id);
        this.start = start;
        this.end = end;
        this.idPlane = idPlane;
    }

    public Start getStart() {
        return start;
    }

    public End getEnd() {
        return end;
    }

    public PlaneId getIdPlane() {
        return idPlane;
    }
}
