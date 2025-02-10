package ec.com.airsofka.flight.values;

import ec.com.airsofka.generics.utils.Identity;

public class FlightId extends Identity {
    public FlightId() {
        super();
    }

    private FlightId(final String id) {
        super(id);
    }

    public static FlightId of(final String id) {
        return new FlightId(id);
    }
}
