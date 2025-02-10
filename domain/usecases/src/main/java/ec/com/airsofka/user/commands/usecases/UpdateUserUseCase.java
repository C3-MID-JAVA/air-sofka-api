package ec.com.airsofka.user.commands.usecases;

import ec.com.airsofka.aggregate.auth.Auth;
import ec.com.airsofka.aggregate.auth.events.EventsAuthEnum;
import ec.com.airsofka.aggregate.auth.events.UserCreated;
import ec.com.airsofka.gateway.BusEvent;
import ec.com.airsofka.gateway.IEventStore;
import ec.com.airsofka.generics.domain.DomainEvent;
import ec.com.airsofka.generics.interfaces.IUseCaseExecute;
import ec.com.airsofka.user.commands.UpdateUserCommand;
import ec.com.airsofka.user.queries.responses.UserResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

public class UpdateUserUseCase implements IUseCaseExecute<UpdateUserCommand, UserResponse> {

    private final IEventStore eventRepository;

    private final BusEvent busEvent;

    public UpdateUserUseCase(IEventStore eventRepository, BusEvent busEvent) {
        this.eventRepository = eventRepository;
        this.busEvent = busEvent;
    }


    @Override
    public Mono<UserResponse> execute(UpdateUserCommand cmd) {
        Mono<UserCreated> userCreatedEvent = eventRepository.findAllAggregateByEvent("auth",EventsAuthEnum.USER_CREATED.name())
                .map(event -> (UserCreated) event)
                .filter(event -> event.getUserId().equals(cmd.getUserId()))
                .single();

        return userCreatedEvent.flatMap(userUpdated -> {
            Flux<DomainEvent> eventsUser = eventRepository.findAggregate(userUpdated.getAggregateRootId(),"auth");


            return Auth.from(userUpdated.getAggregateRootId(), eventsUser)
                    .flatMap(user -> {
                        user.updateUser(
                                userUpdated.getUserId(),
                                userUpdated.getBirthDate(),
                                userUpdated.getDocumentNumber(),
                                userUpdated.getDocumentType(),
                                userUpdated.getEmail(),
                                userUpdated.getFirstLastName(),
                                cmd.getFrequent(),
                                userUpdated.getLastLastName(),
                                userUpdated.getName(),
                                cmd.getNumberOfFlights(),
                                userUpdated.getPassword(),
                                userUpdated.getPhone(),
                                userUpdated.getPrefix(),
                                userUpdated.getRole(),
                                userUpdated.getTitle());

                        // Guardar los eventos no comprometidos de forma reactiva
                        return Flux.fromIterable(user.getUncommittedEvents())
                                .flatMap(eventRepository::save) // Guardar cada evento en el EventStore
                                .doOnNext(updateEvent -> busEvent.sendEventUserUpdated(Mono.just(updateEvent))) // Enviar eventos a través de BusEvent
                                .then(Mono.defer(() -> {
                                    // Marcar los eventos como comprometidos
                                    user.markEventsAsCommitted();

                                    // Crear y devolver la respuesta
                                    return Mono.just(new UserResponse(
                                            user.getUser().getId().getValue(),
                                            user.getUser().getBirthDate().getValue(),
                                            user.getUser().getDocumentNumber().getValue(),
                                            user.getUser().getDocumentType(),
                                            user.getUser().getEmail().getValue(),
                                            user.getUser().getFirstLastName().getValue(),
                                            user.getUser().getFrequent().getValue(),
                                            user.getUser().getLastLastName().getValue(),
                                            user.getUser().getName().getValue(),
                                            user.getUser().getNumberOfFlights().getValue(),
                                            user.getUser().getPassword().getValue(),
                                            user.getUser().getPhone().getValue(),
                                            user.getUser().getPrefix().getValue(),
                                            user.getUser().getRole(),
                                            user.getUser().getTitle().getValue()
                                            ));
                                }));
                    });
        });
    }
}
