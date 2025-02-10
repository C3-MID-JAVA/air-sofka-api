package ec.com.airsofka.user.commands.usecases;

import ec.com.airsofka.aggregate.auth.Auth;
import ec.com.airsofka.exceptions.EmailAlreadyExistsException;
import ec.com.airsofka.gateway.BusEvent;
import ec.com.airsofka.gateway.IEventStore;
import ec.com.airsofka.gateway.IUserRepository;
import ec.com.airsofka.generics.interfaces.IUseCaseExecute;
import ec.com.airsofka.user.User;
import ec.com.airsofka.user.commands.CreateUserCommand;
import ec.com.airsofka.user.queries.responses.UserResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
public class CreateUserUsecase implements IUseCaseExecute<CreateUserCommand, UserResponse>{

    private final IEventStore eventRepository;
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BusEvent busEvent;

    public CreateUserUsecase(IEventStore eventRepository, IUserRepository userRepository, PasswordEncoder passwordEncoder, BusEvent busEvent) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.busEvent = busEvent;
    }


    private Mono<String> validateEmailNotExists(String email) {
        return userRepository.findByEmail(email)
                .<String>flatMap(user -> Mono.error(new EmailAlreadyExistsException("El email ya existe en la base de datos")))
                .switchIfEmpty(Mono.just(email));
    }

    @Override
    public Mono<UserResponse> execute(CreateUserCommand cmd) {
        return validateEmailNotExists(cmd.getEmail())
                .flatMap(valid -> createUser(cmd));
    }

    private Mono<UserResponse> createUser(CreateUserCommand cmd) {
        Auth authAggregate = new Auth();
        authAggregate.createUser(
                cmd.getBirthDate(),
                cmd.getDocumentNumber(),
                cmd.getDocumentType(),
                cmd.getEmail(),
                cmd.getFirstLastName(),
                cmd.getFrequent(),
                cmd.getLastLastName(),
                cmd.getName(),
                cmd.getNumberOfFlights(),
                passwordEncoder.encode(cmd.getPassword()),
                cmd.getPhone(),
                cmd.getPrefix(),
                cmd.getRole(),
                cmd.getTitle()
        );

        authAggregate.getUncommittedEvents()
                .stream()
                .map(eventRepository::save)
                .forEach(busEvent::sendEventUserCreated);

        User newUser = authAggregate.getUser();
        authAggregate.markEventsAsCommitted();

        return Mono.just(new UserResponse(
                newUser.getId().getValue(),
                newUser.getBirthDate().getValue(),
                newUser.getDocumentNumber().getValue(),
                newUser.getDocumentType(),
                newUser.getEmail().getValue(),
                newUser.getFirstLastName().getValue(),
                newUser.getFrequent().getValue(),
                newUser.getLastLastName().getValue(),
                newUser.getName().getValue(),
                newUser.getNumberOfFlights().getValue(),
                newUser.getPassword().getValue(),
                newUser.getPhone().getValue(),
                newUser.getPrefix().getValue(),
                newUser.getRole(),
                newUser.getTitle().getValue()
        ));
    }
}
