package ec.com.airsofka.mapper;

import ec.com.airsofka.data.PassengerEntity;
import ec.com.airsofka.gateway.dto.PassengerDTO;
import ec.com.airsofka.passenger.Passenger;

public class PassengerMapperEntity {

    public static PassengerEntity toPassengerEntity(PassengerDTO passenger) {
        return new PassengerEntity(
                passenger.getId(),
                passenger.getTitle(),
                passenger.getName(),
                passenger.getLastName(),
                passenger.getPassengerType(),
                passenger.getSeatId(),
                passenger.getBookingId()
        );
    }

    public static PassengerDTO toPassengerDTO(PassengerEntity passenger) {
        return new PassengerDTO(
                passenger.getId(),
                passenger.getTitle(),
                passenger.getName(),
                passenger.getLastName(),
                passenger.getPassengerType(),
                passenger.getSeatId(),
                passenger.getIdBooking()
        );
    }
}
