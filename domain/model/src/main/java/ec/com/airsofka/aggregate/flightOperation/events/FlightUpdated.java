package ec.com.airsofka.aggregate.flightOperation.events;

import ec.com.airsofka.generics.domain.DomainEvent;

import java.time.LocalDateTime;

public class FlightUpdated extends DomainEvent {
    private String id;
    private String origin;
    private String destination;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private Double price;
    private String idPlane;

    public FlightUpdated() {
        super(EventsFlighOperationEnum.FLIGHT_UPDATED.name());
    }

    public FlightUpdated(String id, String origin, String destination, LocalDateTime departure, LocalDateTime arrival, Double price, String idPlane) {
        super(EventsFlighOperationEnum.FLIGHT_UPDATED.name());
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.departure = departure;
        this.arrival = arrival;
        this.price = price;
        this.idPlane = idPlane;
    }

    public String getId() {
        return id;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public Double getPrice() {
        return price;
    }

    public String getIdPlane() {
        return idPlane;
    }
}
