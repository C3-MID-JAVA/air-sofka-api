package ec.com.airsofka.aggregate.reservation.events;

import ec.com.airsofka.generics.utils.Identity;

public class PassengerListId extends Identity {
    public PassengerListId() {
        super();
    }

    private PassengerListId(final String id) {
        super(id);
    }

    public static PassengerListId of(final String id) {
        return new PassengerListId(id);
    }
}
