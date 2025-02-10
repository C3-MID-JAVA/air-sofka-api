package ec.com.airsofka.maintenance.queries.usecases;

import ec.com.airsofka.gateway.MaintenanceRepository;
import ec.com.airsofka.gateway.dto.MaintenanceDTO;
import ec.com.airsofka.generics.interfaces.IUseCaseAccept;

public class MaintenanceSavedViewUseCase implements IUseCaseAccept<MaintenanceDTO, Void> {
    private final MaintenanceRepository maintenanceRepository;

    public MaintenanceSavedViewUseCase(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    @Override
    public void accept(MaintenanceDTO maintenanceDTO) {
        maintenanceRepository.save(maintenanceDTO).subscribe();
    }
}
