package ec.com.airsofka.passenger;

import ec.com.airsofka.booking.values.BookingId;
import ec.com.airsofka.generics.utils.Entity;
import ec.com.airsofka.passenger.values.PassengerId;
import ec.com.airsofka.passenger.values.objects.LastName;
import ec.com.airsofka.passenger.values.objects.PassengerType;
import ec.com.airsofka.seat.values.SeatId;
import ec.com.airsofka.user.values.objects.Name;
import ec.com.airsofka.user.values.objects.Title;

public class Passenger extends Entity<PassengerId> {
    private final Title title;
    private final Name name;
    private final LastName lastName;
    private final PassengerType passengerType;
    private final SeatId  seatId;
    private final BookingId bookingId;


    public Passenger(PassengerId id, Title title, Name name, LastName lastName, PassengerType passengerType, SeatId seatId, BookingId bookingId) {
        super(id);
        this.title = title;
        this.name = name;
        this.lastName = lastName;
        this.passengerType = passengerType;
        this.seatId = seatId;
        this.bookingId = bookingId;
    }

    public Title getTitle() {
        return title;
    }

    public Name getName() {
        return name;
    }

    public LastName getLastName() {
        return lastName;
    }

    public PassengerType getPassengerType() {
        return passengerType;
    }

    public SeatId getSeatId() {
        return seatId;
    }

    public BookingId getBookingId() {
        return bookingId;
    }
}
