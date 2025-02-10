package ec.com.airsofka.user.queries.usecases;

import ec.com.airsofka.gateway.IEventStore;
import ec.com.airsofka.gateway.IUserRepository;
import ec.com.airsofka.generics.interfaces.IUseCaseAccept;
import ec.com.airsofka.user.commands.UpdateUserCommand;
import ec.com.airsofka.user.commands.usecases.UpdateUserUseCase;
import ec.com.airsofka.user.queries.query.GetByElementQuery;
import reactor.core.publisher.Mono;
import ec.com.airsofka.utils.mappers.UserMapper;
import reactor.core.scheduler.Schedulers;

import java.util.NoSuchElementException;

public class FrequentUserUseCase implements IUseCaseAccept<GetByElementQuery, Void> {

    private final IUserRepository userRepository;

    private final IEventStore eventRepository;

    private final UpdateUserUseCase updateUserUseCase;

    public FrequentUserUseCase(IUserRepository userRepository, IEventStore eventRepository, UpdateUserUseCase updateUserUseCase) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.updateUserUseCase = updateUserUseCase;
    }


    @Override
    public void accept(GetByElementQuery request) {
        userRepository.findById(request.getElement())
                /*.switchIfEmpty(Mono.error(new NoSuchElementException("User not found by id")))*/
                .publishOn(Schedulers.boundedElastic())
                .flatMap(userDTO -> {
                    userDTO.setNumberOfFlights(userDTO.getNumberOfFlights() + 1);
                    if (userDTO.getNumberOfFlights() >= 10) {
                        userDTO.setFrequent(true);
                    }
                    UpdateUserCommand updateUserCommand = UserMapper.toUpdateUserCommand(userDTO);
                    return updateUserUseCase.execute(updateUserCommand);
                })
                .subscribe();
    }

}
