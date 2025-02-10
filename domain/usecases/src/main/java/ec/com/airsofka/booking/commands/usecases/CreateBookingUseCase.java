package ec.com.airsofka.booking.commands.usecases;


import ec.com.airsofka.aggregate.reservation.Reservation;
import ec.com.airsofka.booking.commands.CreateBookingCommand;
import ec.com.airsofka.booking.queries.responses.BookingResponse;
import ec.com.airsofka.gateway.BusEvent;
import ec.com.airsofka.gateway.IEventStore;
import ec.com.airsofka.gateway.data.EmailData;
import ec.com.airsofka.generics.interfaces.IUseCaseExecute;
import ec.com.airsofka.passenger.PassengerCreatedDTO;
import ec.com.airsofka.seat.SeatStatus;
import ec.com.airsofka.seat.commands.UpdateSeatStatusCommand;
import ec.com.airsofka.seat.commands.usecases.UpdateSeatStatusUseCase;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CreateBookingUseCase implements IUseCaseExecute<CreateBookingCommand, BookingResponse> {
    private final IEventStore repository;
    private final BusEvent busEvent;
    private final UpdateSeatStatusUseCase updateSeatStatusUseCase;

    public CreateBookingUseCase(IEventStore repository, BusEvent busEvent, UpdateSeatStatusUseCase updateSeatStatusUseCase) {
        this.repository = repository;
        this.busEvent = busEvent;
        this.updateSeatStatusUseCase = updateSeatStatusUseCase;
    }

    @Override
    public Mono<BookingResponse> execute(CreateBookingCommand cmd) {
        Reservation reservation = new Reservation();


        reservation.createBooking(cmd.getBookingStatus(),
                cmd.getBookingPrice(),
                cmd.getDiscount(),
                cmd.getFlightId(),
                cmd.getUserId());

        reservation.getUncommittedEvents()
                .stream()
                .map(repository::save)
                .forEach(busEvent::sendEventBookingCreated);
        reservation.markEventsAsCommitted();

        String bookingId = reservation.getBooking().getId().getValue();

        List<PassengerCreatedDTO> passengers = cmd.getPassengers().stream()
                .map(command -> new PassengerCreatedDTO(
                        command.getPassengerTitle(),
                        command.getPassengerName(),
                        command.getPassengerLastName(),
                        command.getPassengerType(),
                        command.getSeatId(),
                        bookingId
                )).toList();

        reservation.createBilling(bookingId, cmd.getPaymentMethod(), cmd.getBookingPrice());
        reservation.getUncommittedEvents()
                .stream()
                .map(repository::save)
                .forEach(busEvent::sendEventBillingCreated);
        reservation.markEventsAsCommitted();

        reservation.createContact(bookingId, cmd.getEmail(), cmd.getPrefix(), cmd.getPhoneNumber());
        reservation.getUncommittedEvents()
                .stream()
                .map(repository::save)
                .forEach(busEvent::sendEventContactCreated);
        reservation.markEventsAsCommitted();

        reservation.createPassengers(passengers);

        reservation.getUncommittedEvents()
                .stream()
                .map(repository::save)
                .forEach(busEvent::sendEventPassengerCreated);

        reservation.markEventsAsCommitted();

        busEvent.sendEmailNotification(
                Mono.just(
                        new EmailData(
                                cmd.getEmail(),
                                cmd.getPrefix() + " " + cmd.getPhoneNumber(),
                                cmd.getPassengers().get(0).getPassengerName(),
                                cmd.getDepartureCity(),
                                cmd.getArrivalCity(),
                                cmd.getDepartureDate(),
                                cmd.getArrivalDate(),
                                cmd.getTicketPrice(),
                                cmd.getAirportTax(),
                                cmd.getAdditionalCharges(),
                                cmd.getFuelInsurance(),
                                cmd.getBookingFee(),
                                cmd.getTotalAmount(),
                                cmd.getKeyNotes(),
                                cmd.getPassengers()


                        )
                )
        );

        passengers.forEach(passenger ->
                updateSeatStatusUseCase.execute(
                        new UpdateSeatStatusCommand(passenger.getSeatId(), SeatStatus.OCCUPIED, cmd.getFlightId())
                ).subscribe()
        );

        return Mono.just(new BookingResponse(
                        "RESERVATION CONFIRMED",
                        reservation.getContact().getEmail().getValue(),
                        reservation.getContact().getPhone().getValue(),
                        reservation.getBilling().getTotalPrice().getValue()
                )
        );
    }
}
