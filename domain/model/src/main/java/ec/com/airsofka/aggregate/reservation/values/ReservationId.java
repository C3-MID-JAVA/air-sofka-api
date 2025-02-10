package ec.com.airsofka.aggregate.reservation.values;

import ec.com.airsofka.generics.utils.Identity;

public class ReservationId extends Identity {

    public ReservationId() {
    }

    public ReservationId(String id) {
        super(id);
    }

    public static ReservationId of(final String id) {
        return new ReservationId(id);
    }
}
