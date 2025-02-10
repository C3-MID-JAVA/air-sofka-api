package ec.com.airsofka.flight.queries.usecases;

import ec.com.airsofka.gateway.IFlightRepository;
import ec.com.airsofka.gateway.dto.FlightDTO;
import ec.com.airsofka.generics.interfaces.IUseCaseAccept;

public class FlightUpdateViewUseCase implements IUseCaseAccept<FlightDTO, Void>  {
    private final IFlightRepository flightRepository;

    public FlightUpdateViewUseCase(IFlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public void accept(FlightDTO request) {
        flightRepository.save(request).subscribe();
    }
}
