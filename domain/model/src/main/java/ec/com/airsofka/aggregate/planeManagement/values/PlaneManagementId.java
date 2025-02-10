package ec.com.airsofka.aggregate.planeManagement.values;

import ec.com.airsofka.aggregate.flightOperation.values.FlightOperationId;
import ec.com.airsofka.generics.utils.Identity;

public class PlaneManagementId extends Identity {
    public PlaneManagementId() {
        super();
    }

    public PlaneManagementId(String id) {
        super(id);
    }

    public static PlaneManagementId of(final String id) {
        return new PlaneManagementId(id);
    }
}
