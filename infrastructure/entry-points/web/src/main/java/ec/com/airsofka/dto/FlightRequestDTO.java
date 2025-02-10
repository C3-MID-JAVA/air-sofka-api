package ec.com.airsofka.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public class FlightRequestDTO {

    @NotBlank(message = "Origin cannot be blank")
    private String origin;

    @NotBlank(message = "Origin cannot be blank")
    private String destination;

    @NotNull(message = "Origin cannot be null")
    private LocalDateTime departure;

    @NotNull(message = "Origin cannot be null")
    private LocalDateTime arrival;

    @Positive(message = "Price must be greater than 0")
    private Double price;

    @NotBlank(message = "Origin cannot be blank")
    private String idPlane;

    public FlightRequestDTO(String origin, String destination, LocalDateTime departure, LocalDateTime arrival, Double price, String idPlane) {
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
