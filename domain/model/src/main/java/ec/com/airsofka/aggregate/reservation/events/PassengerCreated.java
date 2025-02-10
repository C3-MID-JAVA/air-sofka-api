package ec.com.airsofka.aggregate.reservation.events;


import ec.com.airsofka.generics.domain.DomainEvent;

public class PassengerCreated extends DomainEvent {
    private final String id;
    private final String title;
    private final String name;
    private final String lastName;
    private final String passengerType;
    private final String seatId;
    private final String bookingId;

    public PassengerCreated(String id, String title, String name, String lastName, String passengerType, String seatId, String bookingId) {
        super(EventsReservationEnum.PASSENGER_CREATED.name());
        this.id = id;
        this.title = title;
        this.name = name;
        this.lastName = lastName;
        this.passengerType = passengerType;
        this.seatId = seatId;
        this.bookingId = bookingId;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassengerType() {
        return passengerType;
    }

    public String getSeatId() {
        return seatId;
    }

    public String getBookingId() {
        return bookingId;
    }
}
