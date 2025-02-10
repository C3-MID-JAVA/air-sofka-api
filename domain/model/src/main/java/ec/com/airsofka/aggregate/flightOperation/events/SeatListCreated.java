package ec.com.airsofka.aggregate.flightOperation.events;

import ec.com.airsofka.generics.domain.DomainEvent;
import ec.com.airsofka.seat.SeatCreatedDTO;

import java.util.List;

public class SeatListCreated extends DomainEvent {
    private String id;
    private List<SeatCreatedDTO> seats;

    public SeatListCreated() {
        super(EventsFlighOperationEnum.SEAT_LIST_CREATED.name());
    }

    public SeatListCreated(String id, List<SeatCreatedDTO> seats) {
        super(EventsFlighOperationEnum.SEAT_LIST_CREATED.name());
        this.id = id;
        this.seats = seats;
    }

    public String getId() {
        return id;
    }

    public List<SeatCreatedDTO> getSeats() {
        return seats;
    }
}
