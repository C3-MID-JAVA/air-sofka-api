package ec.com.airsofka.flight;

import ec.com.airsofka.flight.values.FlightId;
import ec.com.airsofka.flight.values.objects.*;
import ec.com.airsofka.generics.utils.Entity;
import ec.com.airsofka.plane.values.PlaneId;

public class Flight extends Entity<FlightId> {
    private final Origin origin;
    private final Destination destination;
    private final Departure departure;
    private final Arrival arrival;
    private final Price price;
    private final PlaneId idPlane;

    public Flight(FlightId id, Origin origin, Destination destination, Departure departure, Arrival arrival, Price price, PlaneId idPlane) {
        super(id);
        this.origin = origin;
        this.destination = destination;
        this.departure = departure;
        this.arrival = arrival;
        this.price = price;
        this.idPlane = idPlane;
    }

    public Origin getOrigin() {
        return origin;
    }

    public Destination getDestination() {
        return destination;
    }

    public Departure getDeparture() {
        return departure;
    }

    public Arrival getArrival() {
        return arrival;
    }

    public Price getPrice() {
        return price;
    }

    public PlaneId getIdPlane() {
        return idPlane;
    }
}
