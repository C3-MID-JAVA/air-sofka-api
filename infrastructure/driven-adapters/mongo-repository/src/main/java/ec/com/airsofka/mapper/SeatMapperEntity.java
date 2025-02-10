package ec.com.airsofka.mapper;

import ec.com.airsofka.data.SeatEntity;
import ec.com.airsofka.gateway.dto.SeatDTO;

public class SeatMapperEntity {
    public static SeatEntity toEntity(SeatDTO seatDTO) {
        return new SeatEntity(
                seatDTO.getId(),
                seatDTO.getNumber(),
                seatDTO.getRow(),
                seatDTO.getColumn(),
                seatDTO.getType(),
                seatDTO.getStatus(),
                seatDTO.getPrice(),
                seatDTO.getIdFlight()
        );
    }

    public static SeatDTO fromEntity(SeatEntity seatEntity) {
        return new SeatDTO(
                seatEntity.getId(),
                seatEntity.getNumber(),
                seatEntity.getRow(),
                seatEntity.getColumn(),
                seatEntity.getType(),
                seatEntity.getStatus(),
                seatEntity.getPrice(),
                seatEntity.getIdFlight()
        );
    }
}
