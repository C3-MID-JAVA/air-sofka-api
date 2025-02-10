package ec.com.airsofka.plane.queries.usecases;

import ec.com.airsofka.gateway.PlaneRepository;
import ec.com.airsofka.gateway.dto.PlaneDTO;
import ec.com.airsofka.generics.interfaces.IUseCaseAccept;

public class PlaneSavedViewUseCase implements IUseCaseAccept<PlaneDTO, Void> {
    private final PlaneRepository planeRepository;

    public PlaneSavedViewUseCase(PlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
    }

    @Override
    public void accept(PlaneDTO planeDTO) {
        planeRepository.save(planeDTO).subscribe();
    }
}
