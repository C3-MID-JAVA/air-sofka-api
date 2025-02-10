package ec.com.airsofka.aggregate.reservation.events;

import ec.com.airsofka.generics.domain.DomainEvent;
import ec.com.airsofka.passenger.PassengerCreatedDTO;

import java.util.List;

public class PassengerListCreated extends DomainEvent {
    private final   String id;
    private final   List<PassengerCreatedDTO> passengers;



    public PassengerListCreated(String id, List<PassengerCreatedDTO> passengers) {
        super(EventsReservationEnum.PASSENGER_LIST_CREATED.name());
        this.id = id;
        this.passengers = passengers;
    }

    public String getId() {
        return id;
    }

    public List<PassengerCreatedDTO> getPassengers() {
        return passengers;
    }
}
