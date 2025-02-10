package ec.com.airsofka.flight.queries.usecases;

import ec.com.airsofka.gateway.IFlightRepository;
import ec.com.airsofka.gateway.dto.FlightDTO;
import ec.com.airsofka.generics.interfaces.IUseCaseAccept;

public class FlightSavedViewUseCase implements IUseCaseAccept<FlightDTO, Void> {

    private final IFlightRepository flightRepository;

    public FlightSavedViewUseCase(IFlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public void accept(FlightDTO flightDTO) {
        flightRepository.save(flightDTO).subscribe();
    }
}
