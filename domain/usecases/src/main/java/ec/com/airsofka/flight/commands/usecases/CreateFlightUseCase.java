package ec.com.airsofka.flight.commands.usecases;

import ec.com.airsofka.aggregate.flightOperation.FlightOperation;
import ec.com.airsofka.flight.Flight;
import ec.com.airsofka.flight.commands.CreateFlightCommand;
import ec.com.airsofka.flight.queries.responses.FlightResponse;
import ec.com.airsofka.gateway.BusEvent;
import ec.com.airsofka.gateway.IEventStore;
import ec.com.airsofka.generics.interfaces.IUseCaseExecute;
import ec.com.airsofka.seat.SeatData;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class CreateFlightUseCase implements IUseCaseExecute<CreateFlightCommand, FlightResponse> {
    private final IEventStore repository;
    private final BusEvent busEvent;

    public CreateFlightUseCase(IEventStore repository, BusEvent busEvent) {
        this.repository = repository;
        this.busEvent = busEvent;
    }

    @Override
    public Mono<FlightResponse> execute(CreateFlightCommand cmd) {
        FlightOperation flightOperation = new FlightOperation();

        flightOperation.createFlight(cmd.getOrigin(), cmd.getDestination(), cmd.getDeparture(), cmd.getArrival(), cmd.getPrice(), cmd.getIdPlane());

        flightOperation.getUncommittedEvents()
                .stream()
                .map(repository::save)
                .forEach(busEvent::sendEventFlightCreated);
        flightOperation.markEventsAsCommitted();


        flightOperation.createSeatList(SeatData.getSeatList(flightOperation.getFlight().getId().getValue()));

        flightOperation.getUncommittedEvents()
                .stream()
                .map(repository::save)
                .forEach(busEvent::sendEventSeatListCreated);
        flightOperation.markEventsAsCommitted();

        Flight newFlight = flightOperation.getFlight();
        return Mono.just(new FlightResponse(
                        newFlight.getId().getValue(),
                        newFlight.getOrigin().getValue(),
                        newFlight.getDestination().getValue(),
                        newFlight.getDeparture().getValue(),
                        newFlight.getArrival().getValue(),
                        newFlight.getPrice().getValue(),
                        newFlight.getIdPlane().getValue()
                )
        );
    }
}
