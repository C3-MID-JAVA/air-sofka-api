package ec.com.airsofka.booking;

import ec.com.airsofka.booking.values.BookingId;
import ec.com.airsofka.booking.values.objects.Discount;
import ec.com.airsofka.booking.values.objects.Status;
import ec.com.airsofka.booking.values.objects.TotalPrice;
import ec.com.airsofka.flight.values.FlightId;
import ec.com.airsofka.generics.utils.Entity;
import ec.com.airsofka.user.values.UserId;

public class Booking extends Entity<BookingId> {
    private final Status status;
    private final TotalPrice totalPrice;
    private final Discount discount;
    private final FlightId flightId;
    private final UserId userId;


    public Booking(BookingId id, Status status, TotalPrice totalPrice, Discount discount, FlightId flightId, UserId userId) {
        super(id);
        this.status = status;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.flightId = flightId;
        this.userId = userId;
    }

    public Status getStatus() {
        return status;
    }

    public TotalPrice getTotalPrice() {
        return totalPrice;
    }

    public Discount getDiscount() {
        return discount;
    }

    public FlightId getFlightId() {
        return flightId;
    }

    public UserId getUserId() {
        return userId;
    }
}
