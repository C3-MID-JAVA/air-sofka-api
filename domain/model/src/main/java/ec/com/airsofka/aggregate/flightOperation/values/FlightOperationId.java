package ec.com.airsofka.aggregate.flightOperation.values;

import ec.com.airsofka.generics.utils.Identity;

public class FlightOperationId extends Identity {
    public FlightOperationId() {
        super();
    }

    public FlightOperationId(String id) {
        super(id);
    }

    public static FlightOperationId of(final String id) {
        return new FlightOperationId(id);
    }
}
