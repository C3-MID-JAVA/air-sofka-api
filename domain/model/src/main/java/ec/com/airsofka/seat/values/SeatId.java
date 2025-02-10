package ec.com.airsofka.seat.values;

import ec.com.airsofka.generics.utils.Identity;

public class SeatId extends Identity {
    public SeatId() {
        super();
    }

    private SeatId(final String id) {
        super(id);
    }

    public static SeatId of(final String id) {
        return new SeatId(id);
    }
}
