package ec.com.airsofka.mapper;

import ec.com.airsofka.data.FlightEntity;
import ec.com.airsofka.gateway.dto.FlightDTO;

public class FlightMapperEntity {
    public static FlightEntity toEntity(FlightDTO flightDTO) {
        return new FlightEntity(
                flightDTO.getId(),
                flightDTO.getOrigin(),
                flightDTO.getDestination(),
                flightDTO.getDeparture(),
                flightDTO.getArrival(),
                flightDTO.getPrice(),
                flightDTO.getIdPlane()
        );
    }

    public static FlightDTO fromEntity(FlightEntity fligthEntity) {
        return new FlightDTO(
                fligthEntity.getId(),
                fligthEntity.getOrigin(),
                fligthEntity.getDestination(),
                fligthEntity.getDeparture(),
                fligthEntity.getArrival(),
                fligthEntity.getPrice(),
                fligthEntity.getIdPlane()
        );
    }
}
