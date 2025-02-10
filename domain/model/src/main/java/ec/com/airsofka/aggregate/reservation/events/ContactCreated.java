package ec.com.airsofka.aggregate.reservation.events;


import ec.com.airsofka.generics.domain.DomainEvent;

public class ContactCreated extends DomainEvent {

    private final String id;
    private final String email;
    private final String prefix;
    private final String phone;
    private  final String bookingId;

    public ContactCreated(String id, String email, String prefix, String phone, String bookingId) {
        super(EventsReservationEnum.CONTACT_CREATED.name());
        this.id = id;
        this.email = email;
        this.prefix = prefix;
        this.phone = phone;
        this.bookingId = bookingId;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getPhone() {
        return phone;
    }

    public String getBookingId() {
        return bookingId;
    }
}
