package ec.com.airsofka.booking.queries.query;

import ec.com.airsofka.gateway.dto.PassengerDTO;
import ec.com.airsofka.gateway.dto.SeatDTO;
import ec.com.airsofka.generics.utils.Query;

import java.util.List;

public class GetAmountsQuery extends Query {
    private final String userId;
    private final List<PassengerDTO> passengers;

    public GetAmountsQuery(List<PassengerDTO> passengers, String userId) {
        super(null);
        this.passengers = passengers;
        this.userId = userId;
    }

    public List<PassengerDTO> getPassengers() {
        return passengers;
    }

    public String getUserId() {
        return userId;
    }
}
