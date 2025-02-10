package ec.com.airsofka.aggregate.reservation.events;

import ec.com.airsofka.booking.values.objects.Discount;
import ec.com.airsofka.booking.values.objects.TotalPrice;
import ec.com.airsofka.flight.values.FlightId;
import ec.com.airsofka.generics.domain.DomainEvent;
import ec.com.airsofka.user.values.UserId;

import java.math.BigDecimal;

public class BookingCreated  extends DomainEvent {

    private final String id;
    private final String status;
    private final BigDecimal totalPrice;
    private final BigDecimal discount;
    private final String flightId;
    private final String userId;

    public BookingCreated(String id, String status, BigDecimal totalPrice, BigDecimal discount, String flightId, String userId) {
        super(EventsReservationEnum.BOOKING_CREATED.name());

        this.id = id;
        this.status = status;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.flightId = flightId;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public String getFlightId() {
        return flightId;
    }

    public String getUserId() {
        return userId;
    }
}
