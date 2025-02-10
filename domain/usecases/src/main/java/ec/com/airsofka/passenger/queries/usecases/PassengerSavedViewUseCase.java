package ec.com.airsofka.passenger.queries.usecases;

import ec.com.airsofka.gateway.IPassengerRepository;
import ec.com.airsofka.gateway.dto.PassengerDTO;
import ec.com.airsofka.generics.interfaces.IUseCaseAccept;

public class PassengerSavedViewUseCase  implements IUseCaseAccept<PassengerDTO, Void> {
    private final IPassengerRepository passengerRepository;


    public PassengerSavedViewUseCase(IPassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @Override
    public void accept(PassengerDTO request) {
        passengerRepository.save(request).subscribe();
    }
}
