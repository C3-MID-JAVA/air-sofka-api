package ec.com.airsofka.flight.queries.query;

import ec.com.airsofka.generics.utils.Query;

public class GetAllFlightQuery extends Query {
    private final String origin;
    private final String destination;
    private final String departureDate;
    private final String returnDate;
    private final Integer adults;

    public GetAllFlightQuery(String origin, String destination, String departureDate, String returnDate, Integer adults) {
        super(null);
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.adults = adults;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public Integer getAdults() {
        return adults;
    }
}
