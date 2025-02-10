package ec.com.airsofka.mapper;

import ec.com.airsofka.dto.FlightRequestDTO;
import ec.com.airsofka.dto.FlightUpdateRequestDTO;
import ec.com.airsofka.flight.commands.CreateFlightCommand;
import ec.com.airsofka.flight.commands.UpdateFlightCommand;

public class FlightMapper {

    public static CreateFlightCommand toCommand(FlightRequestDTO flightRequestDTO) {
        return new CreateFlightCommand(
                flightRequestDTO.getOrigin(),
                flightRequestDTO.getDestination(),
                flightRequestDTO.getDeparture(),
                flightRequestDTO.getArrival(),
                flightRequestDTO.getPrice(),
                flightRequestDTO.getIdPlane()
        );
    }

    public static UpdateFlightCommand toCommand(FlightUpdateRequestDTO flightUpdateRequestDTO) {
        return new UpdateFlightCommand(
                flightUpdateRequestDTO.getId(),
                flightUpdateRequestDTO.getOrigin(),
                flightUpdateRequestDTO.getDestination(),
                flightUpdateRequestDTO.getDeparture(),
                flightUpdateRequestDTO.getArrival(),
                flightUpdateRequestDTO.getPrice(),
                flightUpdateRequestDTO.getIdPlane()
        );
    }
}
