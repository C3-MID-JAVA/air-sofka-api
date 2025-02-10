package ec.com.airsofka.flight.queries.responses;

import java.time.LocalDateTime;

public class FlightResponse {
    private final String id;
    private final String origin;
    private final String destination;
    private final LocalDateTime departure;
    private final LocalDateTime arrival;
    private final Double price;
    private final String idPlane;

    public FlightResponse(String id, String origin, String destination, LocalDateTime departure, LocalDateTime arrival, Double price, String idPlane) {
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
