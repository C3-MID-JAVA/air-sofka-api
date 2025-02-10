package ec.com.airsofka.aggregate.reservation;


import ec.com.airsofka.aggregate.flightOperation.events.SeatListCreated;
import ec.com.airsofka.aggregate.reservation.events.*;
import ec.com.airsofka.billing.Billing;
import ec.com.airsofka.billing.values.BillingId;
import ec.com.airsofka.billing.values.objects.PaymentMethod;
import ec.com.airsofka.booking.Booking;
import ec.com.airsofka.booking.values.BookingId;
import ec.com.airsofka.booking.values.objects.Discount;
import ec.com.airsofka.booking.values.objects.Status;
import ec.com.airsofka.booking.values.objects.TotalPrice;
import ec.com.airsofka.contact.Contact;
import ec.com.airsofka.contact.values.ContactId;
import ec.com.airsofka.flight.values.FlightId;
import ec.com.airsofka.generics.domain.DomainActionsContainer;
import ec.com.airsofka.passenger.Passenger;
import ec.com.airsofka.passenger.values.PassengerId;
import ec.com.airsofka.passenger.values.objects.LastName;
import ec.com.airsofka.passenger.values.objects.PassengerType;
import ec.com.airsofka.seat.Seat;
import ec.com.airsofka.seat.values.SeatId;
import ec.com.airsofka.seat.values.objects.Column;
import ec.com.airsofka.seat.values.objects.Number;
import ec.com.airsofka.seat.values.objects.Row;
import ec.com.airsofka.seat.values.objects.Type;
import ec.com.airsofka.user.values.UserId;
import ec.com.airsofka.user.values.objects.*;

import java.util.List;

public class ReservationHandler extends DomainActionsContainer {
    public ReservationHandler(Reservation reservation) {
        addDomainActions((BookingCreated event) -> {
            Booking booking = new Booking(
                    BookingId.of(event.getId()),
                    Status.of(event.getStatus()),
                    TotalPrice.of(event.getTotalPrice()),
                    Discount.of(event.getDiscount()),
                    FlightId.of(event.getFlightId()),
                    UserId.of(event.getUserId())
            );

            reservation.setBooking(booking);
        });

        addDomainActions((ContactCreated event) -> {
            Contact contact = new Contact(
                    ContactId.of(event.getId()),
                    Email.of(event.getEmail()),
                    Prefix.of(event.getPrefix()),
                    Phone.of(event.getPhone()),
                    BookingId.of(event.getBookingId())

            );

            reservation.setContact(contact);
        });

        addDomainActions((BillingCreated event) -> {
            Billing bill = new Billing(
                    BillingId.of(event.getId()),
                    PaymentMethod.of(event.getPaymentMethod()),
                    TotalPrice.of(event.getTotalPrice()),
                    BookingId.of(event.getBookingId())


            );

            reservation.setBilling(bill);
        });


        addDomainActions((PassengerCreated event) -> {
            Passenger passenger = new Passenger(
                    PassengerId.of(event.getId()),
                    Title.of(event.getTitle()),
                    Name.of(event.getName()),
                    LastName.of(event.getLastName()),
                    PassengerType.of(event.getPassengerType()),
                    SeatId.of(event.getSeatId()),
                    BookingId.of(event.getBookingId())


            );

            reservation.getPassengers().add(passenger);
        });
        addDomainActions((PassengerListCreated event) -> {
            List<Passenger> passengers = event.getPassengers().stream()
                    .map(passenger -> new Passenger(
                            PassengerId.of(passenger.getId()),
                            Title.of(passenger.getTitle()),
                            Name.of(passenger.getName()),
                            LastName.of(passenger.getLastName()),
                            PassengerType.of(passenger.getPassengerType()),
                            SeatId.of(passenger.getSeatId()),
                            BookingId.of(passenger.getBookingId())

                    ))
                    .toList();
            reservation.setPassengers(passengers);

        });
    }
}
