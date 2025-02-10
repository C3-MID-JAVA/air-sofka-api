package ec.com.airsofka.billing;

import ec.com.airsofka.billing.values.BillingId;
import ec.com.airsofka.billing.values.objects.PaymentMethod;
import ec.com.airsofka.booking.values.BookingId;
import ec.com.airsofka.booking.values.objects.TotalPrice;
import ec.com.airsofka.generics.utils.Entity;

public class Billing extends Entity<BillingId> {
    private final PaymentMethod paymentMethod;
    private final TotalPrice totalPrice;
    private final BookingId bookingId;


    public Billing(BillingId id, PaymentMethod paymentMethod, TotalPrice totalPrice, BookingId bookingId) {
        super(id);
        this.paymentMethod = paymentMethod;
        this.totalPrice = totalPrice;
        this.bookingId = bookingId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public TotalPrice getTotalPrice() {
        return totalPrice;
    }

    public BookingId getBookingId() {
        return bookingId;
    }
}
