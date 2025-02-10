package ec.com.airsofka.aggregate.flightOperation;

import ec.com.airsofka.aggregate.flightOperation.events.*;
import ec.com.airsofka.aggregate.flightOperation.values.FlightOperationId;
import ec.com.airsofka.flight.Flight;
import ec.com.airsofka.flight.values.FlightId;
import ec.com.airsofka.generics.domain.DomainEvent;
import ec.com.airsofka.generics.utils.AggregateRoot;
import ec.com.airsofka.seat.Seat;
import ec.com.airsofka.seat.SeatClass;
import ec.com.airsofka.seat.SeatCreatedDTO;
import ec.com.airsofka.seat.SeatStatus;
import ec.com.airsofka.seat.values.SeatId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class FlightOperation extends AggregateRoot<FlightOperationId> {
    private Flight flight;
    private List<Seat> seatList;

    public FlightOperation() {
        super(new FlightOperationId());
        setSubscription(new FlightOperationHandler(this));
    }

    public FlightOperation(final String id) {
        super(FlightOperationId.of(id));
        setSubscription(new FlightOperationHandler(this));
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public void createFlight(String origin, String destination, LocalDateTime departure, LocalDateTime arrival, Double price, String idPlane) {
        addEvent(new FlightCreated(new FlightId().getValue(), origin, destination, departure, arrival, price, idPlane)).apply();
    }

    public void updateFlight(String id, String origin, String destination, LocalDateTime departure, LocalDateTime arrival, Double price, String idPlane) {
        addEvent(new FlightUpdated(id, origin, destination, departure, arrival, price, idPlane)).apply();
    }

    public List<Seat> getSeatList(){return seatList;}

    public void setSeatList(List<Seat> seatList){this.seatList = seatList;}

    public void createSeatList(List<SeatCreatedDTO> seatList){
        addEvent(new SeatListCreated(new SeatListId().getValue(), seatList)).apply();
    }

            /*Actualiza la lista de asientos. Funciona como un update */
    public void createSeatReservation(String id, String number, Integer row, String column, SeatClass type, SeatStatus status, BigDecimal price, String idFlight) {
        addEvent(new SeatReserved(id, number, row, column, type, status, price, idFlight)).apply();
    }


    public static Mono<FlightOperation> from(final String id, Flux<DomainEvent> events) {
        FlightOperation flightOperation = new FlightOperation(id);

        return events
                .filter(eventsFilter -> id.equals(eventsFilter.getAggregateRootId()))
                .flatMap(event -> Mono.fromRunnable(() -> flightOperation.addEvent(event).apply()))
                .doOnTerminate(flightOperation::markEventsAsCommitted)
                .then(Mono.just(flightOperation));
    }
}
