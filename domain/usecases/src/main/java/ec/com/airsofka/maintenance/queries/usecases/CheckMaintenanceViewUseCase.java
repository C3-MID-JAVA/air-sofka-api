package ec.com.airsofka.maintenance.queries.usecases;

import ec.com.airsofka.gateway.MaintenanceRepository;
import ec.com.airsofka.gateway.PlaneRepository;
import ec.com.airsofka.gateway.dto.MaintenanceDTO;
import ec.com.airsofka.gateway.dto.PlaneDTO;
import ec.com.airsofka.generics.interfaces.IUseCaseAccept;
import ec.com.airsofka.plane.PlaneStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class CheckMaintenanceViewUseCase {
    private final MaintenanceRepository repository;
    private final PlaneRepository planeRepository;

    public CheckMaintenanceViewUseCase(MaintenanceRepository repository, PlaneRepository planeRepository) {
        this.repository = repository;
        this.planeRepository = planeRepository;
    }

    public Flux<MaintenanceDTO> accept() {
        Flux<MaintenanceDTO> ongoingMaintenances = repository.findByStartNow()
                .switchIfEmpty(Flux.empty())
                .flatMap(maintenanceDTO ->
                        planeRepository.getById(maintenanceDTO.getIdPlane())
                                .switchIfEmpty(Mono.empty())
                                .flatMap(planeDTO -> {
                                    if (planeDTO.getState() == PlaneStatus.DISABLED) {
                                        return Mono.empty();
                                    }
                                    PlaneDTO plane = new PlaneDTO(
                                            planeDTO.getId(),
                                            PlaneStatus.DISABLED,
                                            planeDTO.getModel()
                                    );
                                    return planeRepository.save(plane)
                                            .then(Mono.just(maintenanceDTO));
                                })
                );

        Flux<MaintenanceDTO> finishedMaintenances = repository.findByEnd()
                .switchIfEmpty(Flux.empty())
                .flatMap(maintenance ->
                        planeRepository.getById(maintenance.getIdPlane())
                                .switchIfEmpty(Mono.empty())
                                .flatMap(planeDTO -> {
                                    if (planeDTO.getState() == PlaneStatus.ENABLED) {
                                        return Mono.empty();
                                    }
                                    PlaneDTO plane = new PlaneDTO(
                                            planeDTO.getId(),
                                            PlaneStatus.ENABLED,
                                            planeDTO.getModel()
                                    );
                                    return planeRepository.save(plane)
                                            .then(Mono.just(maintenance));
                                })
                );

        return Flux.concat(ongoingMaintenances, finishedMaintenances)
                .subscribeOn(Schedulers.parallel());
    }
}
