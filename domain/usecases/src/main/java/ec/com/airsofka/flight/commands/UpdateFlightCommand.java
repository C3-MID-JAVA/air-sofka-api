package ec.com.airsofka.flight.commands;

import ec.com.airsofka.generics.utils.Command;

import java.time.LocalDateTime;

public class UpdateFlightCommand extends Command {
    private final String id;
    private final String origin;
    private final String destination;
    private final LocalDateTime departure;
    private final LocalDateTime arrival;
    private final Double price;
    private final String idPlane;

    public UpdateFlightCommand(String id, String origin, String destination, LocalDateTime departure, LocalDateTime arrival, Double price, String idPlane) {
        super(null);
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
