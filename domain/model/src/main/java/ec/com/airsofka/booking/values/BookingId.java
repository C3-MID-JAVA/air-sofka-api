package ec.com.airsofka.booking.values;

import ec.com.airsofka.generics.utils.Identity;

public class BookingId extends Identity {


    public BookingId() {
        super();
    }

    private BookingId(final String id) {
        super(id);
    }

    public static BookingId of(final String id) {
        return new BookingId(id);
    }
}
