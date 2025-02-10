package ec.com.airsofka.mapper;

import ec.com.airsofka.data.PlaneEntity;
import ec.com.airsofka.gateway.dto.PlaneDTO;

public class PlaneMapperEntity {
    public static PlaneEntity toEntity(PlaneDTO planeDTO) {
        return new PlaneEntity(
                planeDTO.getId(),
                planeDTO.getState(),
                planeDTO.getModel()
        );
    }

    public static PlaneDTO fromEntity(PlaneEntity planeEntity) {
        return new PlaneDTO(
                planeEntity.getId(),
                planeEntity.getState(),
                planeEntity.getModel()
        );
    }
}
