package ec.com.airsofka.flight.commands;

import ec.com.airsofka.generics.utils.Command;

import java.time.LocalDateTime;

public class CreateFlightCommand extends Command {
    private final String origin;
    private final String destination;
    private final LocalDateTime departure;
    private final LocalDateTime arrival;
    private final Double price;
    private final String idPlane;

    public CreateFlightCommand(String origin, String destination, LocalDateTime departure, LocalDateTime arrival, Double price, String idPlane) {
        super(null);;
        this.origin = origin;
        this.destination = destination;
        this.departure = departure;
        this.arrival = arrival;
        this.price = price;
        this.idPlane = idPlane;
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
