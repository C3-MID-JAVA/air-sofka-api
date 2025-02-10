package ec.com.airsofka.seat.commands.usecases;

import ec.com.airsofka.aggregate.flightOperation.FlightOperation;
import ec.com.airsofka.aggregate.flightOperation.events.EventsFlighOperationEnum;
import ec.com.airsofka.aggregate.flightOperation.events.FlightCreated;
import ec.com.airsofka.gateway.BusEvent;
import ec.com.airsofka.gateway.IEventStore;
import ec.com.airsofka.generics.domain.DomainEvent;
import ec.com.airsofka.generics.interfaces.IUseCaseExecute;
import ec.com.airsofka.seat.Seat;
import ec.com.airsofka.seat.commands.UpdateSeatStatusCommand;
import ec.com.airsofka.seat.queries.responses.SeatResponse;
import ec.com.airsofka.seat.values.objects.Status;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class UpdateSeatStatusUseCase implements IUseCaseExecute<UpdateSeatStatusCommand, SeatResponse> {
    private final IEventStore repository;
    private final BusEvent busEvent;

    public UpdateSeatStatusUseCase(IEventStore repository, BusEvent busEvent) {
        this.repository = repository;
        this.busEvent = busEvent;
    }

    @Override
    public Mono<SeatResponse> execute(UpdateSeatStatusCommand cmd) {
        Mono<FlightCreated> flightCreatedEvent = repository.findAllAggregateByEvent("flightOperation", EventsFlighOperationEnum.FLIGHT_CREATED.name())
                .map(event -> (FlightCreated) event)
                .filter(event -> event.getId().equals(cmd.getIdFlight()))
                .single();

        return flightCreatedEvent.flatMap(flightCreated -> {
           Flux<DomainEvent> eventFlights = repository.findAggregate(flightCreated.getAggregateRootId(), "flightOperation");

           return FlightOperation.from(flightCreated.getAggregateRootId(), eventFlights)
                   .flatMap(flightOperation -> {
                       List<Seat> seats = flightOperation.getSeatList();

                       Seat updatedSeat = seats.stream()
                               .filter(seat -> {
                                   return seat.getId().getValue().equals(cmd.getSeatId());
                               })
                               .findFirst()
                               .orElseThrow(() -> new IllegalStateException("Seat not found: " + cmd.getSeatId()));

                       flightOperation.createSeatReservation(
                               updatedSeat.getId().getValue(),
                               updatedSeat.getNumber().getValue(),
                               updatedSeat.getRow().getValue(),
                               updatedSeat.getColumn().getValue(),
                               updatedSeat.getType().getValue(),
                               cmd.getStatus(),
                               updatedSeat.getPrice().getValue(),
                               updatedSeat.getIdFlight().getValue()
                       );

                       flightOperation.getUncommittedEvents()
                               .stream()
                               .map(repository::save)
                               .forEach(busEvent::sendEventSeatReserved);

                       return  Mono.just(new SeatResponse(
                               updatedSeat.getId().getValue(),
                               updatedSeat.getNumber().getValue(),
                               updatedSeat.getRow().getValue(),
                               updatedSeat.getColumn().getValue(),
                               updatedSeat.getType().getValue(),
                               updatedSeat.getStatus().getValue(),
                               updatedSeat.getPrice().getValue(),
                               updatedSeat.getIdFlight().getValue()
                       ));
                   });
        });
    }
}

