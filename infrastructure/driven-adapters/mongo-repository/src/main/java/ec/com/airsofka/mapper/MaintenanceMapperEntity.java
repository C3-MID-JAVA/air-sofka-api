package ec.com.airsofka.mapper;

import ec.com.airsofka.data.MaintenanceEntity;
import ec.com.airsofka.gateway.dto.MaintenanceDTO;

public class MaintenanceMapperEntity {
    public static MaintenanceEntity toEntity(MaintenanceDTO maintenanceDTO) {
        return new MaintenanceEntity(
                maintenanceDTO.getId(),
                maintenanceDTO.getStart(),
                maintenanceDTO.getEnd(),
                maintenanceDTO.getIdPlane()
        );
    }

    public static MaintenanceDTO fromEntity(MaintenanceEntity maintenanceEntity) {
        return new MaintenanceDTO(
                maintenanceEntity.getId(),
                maintenanceEntity.getStart(),
                maintenanceEntity.getEnd(),
                maintenanceEntity.getIdPlane()
        );
    }
}
