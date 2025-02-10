package ec.com.airsofka.aggregate.reservation;


import ec.com.airsofka.aggregate.reservation.events.*;
import ec.com.airsofka.aggregate.reservation.values.ReservationId;
import ec.com.airsofka.billing.Billing;
import ec.com.airsofka.billing.values.BillingId;
import ec.com.airsofka.booking.Booking;
import ec.com.airsofka.booking.values.BookingId;
import ec.com.airsofka.contact.Contact;
import ec.com.airsofka.contact.values.ContactId;
import ec.com.airsofka.generics.domain.DomainEvent;
import ec.com.airsofka.generics.utils.AggregateRoot;
import ec.com.airsofka.passenger.Passenger;
import ec.com.airsofka.passenger.PassengerCreatedDTO;
import ec.com.airsofka.passenger.values.PassengerId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Reservation extends AggregateRoot<ReservationId> {


    private Booking booking;
    private Contact contact;
    private List<Passenger> passengers = new ArrayList<>();
    private Billing billing;

    public Reservation() {
        super(new ReservationId());
        setSubscription(new ReservationHandler(this));
    }

    public Reservation(final String id) {
        super(ReservationId.of(id));
        setSubscription(new ReservationHandler(this));
    }


    public void createBooking(String status, BigDecimal totalPrice, BigDecimal discount, String flightId, String userId) {
        addEvent(new BookingCreated(
                        new BookingId().getValue(),
                        status,
                        totalPrice,
                        discount,
                        flightId,
                        userId
                )
        ).apply();
    }

    public void createBilling(String bookingId, String paymentMethod, BigDecimal totalPrice) {
        addEvent(new BillingCreated(
                        new BillingId().getValue(),
                        paymentMethod,
                        totalPrice,
                        bookingId
                )
        ).apply();

    }

    public void createContact(String bookingId, String email, String prefix, String phone) {
        addEvent(new ContactCreated(
                        new ContactId().getValue(),
                        email,
                        prefix,
                        phone,
                        bookingId
                )

        ).apply();
    }

    public void createPassengers(List<PassengerCreatedDTO> passengers) {

                addEvent(new PassengerListCreated(
                                new PassengerListId().getValue(),
                                passengers
                        )
                ).apply();
    }


    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    public static Mono<Reservation> from(final String id, Flux<DomainEvent> events) {
        Reservation reservation = new Reservation(id);

        return events
                .filter(eventsFilter -> id.equals(eventsFilter.getAggregateRootId()))
                .flatMap(event -> Mono.fromRunnable(() -> reservation.addEvent(event).apply()))
                .doOnTerminate(reservation::markEventsAsCommitted)
                .then(Mono.just(reservation));
    }
}
