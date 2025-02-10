package ec.com.airsofka.aggregate.reservation.events;


import ec.com.airsofka.generics.domain.DomainEvent;

import java.math.BigDecimal;

public class BillingCreated extends DomainEvent {
    private final String id;
    private final String paymentMethod;
    private final BigDecimal totalPrice;
    private final String bookingId;

    public BillingCreated(String id, String paymentMethod, BigDecimal totalPrice, String bookingId) {
        super(EventsReservationEnum.BILLING_CREATED.name());
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.totalPrice = totalPrice;
        this.bookingId = bookingId;
    }

    public String getId() {
        return id;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public String getBookingId() {
        return bookingId;
    }
}
