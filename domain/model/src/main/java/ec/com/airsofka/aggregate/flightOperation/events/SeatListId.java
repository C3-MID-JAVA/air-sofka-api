package ec.com.airsofka.aggregate.flightOperation.events;

import ec.com.airsofka.generics.utils.Identity;

public class SeatListId extends Identity {
    public SeatListId() {
        super();
    }

    private SeatListId(final String id) {
        super(id);
    }

    public static SeatListId of(final String id) {
        return new SeatListId(id);
    }
}