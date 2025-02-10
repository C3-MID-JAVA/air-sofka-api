package ec.com.airsofka.aggregate.auth;

import ec.com.airsofka.aggregate.auth.events.UserCreated;
import ec.com.airsofka.aggregate.auth.events.UserUpdated;
import ec.com.airsofka.aggregate.auth.values.AuthId;
import ec.com.airsofka.generics.domain.DomainEvent;
import ec.com.airsofka.generics.utils.AggregateRoot;
import ec.com.airsofka.user.User;
import ec.com.airsofka.user.values.UserId;
import ec.com.airsofka.user.values.objects.DocumentType;
import ec.com.airsofka.user.values.objects.Role;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class Auth extends AggregateRoot<AuthId> {
    private User user;

    public Auth() {
        super(new AuthId());
        setSubscription(new UserHandler(this));
    }

    public Auth(final String id) {
        super(AuthId.of(id));
        setSubscription(new UserHandler(this));
    }

    public void createUser(LocalDateTime birthDate, String documentNumber, DocumentType documentType,
                           String email, String firstLastName, Boolean frequent, String lastLastName, String name,
                           Integer numberOfFlights, String password, String phone, String prefix, Role role, String title) {

        addEvent(new UserCreated(new UserId().getValue(), birthDate, documentNumber, documentType, email, firstLastName, frequent,
                lastLastName, name, numberOfFlights, password, phone, prefix, role, title)).apply();
    }

    public void updateUser(String userId,LocalDateTime birthDate, String documentNumber, DocumentType documentType,
                           String email, String firstLastName, Boolean frequent, String lastLastName, String name,
                           Integer numberOfFlights, String password, String phone, String prefix, Role role, String title) {

        addEvent(new UserUpdated(userId, birthDate, documentNumber, documentType, email,
                firstLastName, frequent, lastLastName, name, numberOfFlights, password, phone,
                prefix, role, title)).apply();
    }


    public static Mono<Auth> from(final String id, Flux<DomainEvent> events) {
        Auth auth = new Auth(id);
        return events
                .filter(eventsFilter -> id.equals(eventsFilter.getAggregateRootId()))
                .flatMap(event -> Mono.fromRunnable(() -> auth.addEvent(event).apply()))
                .doOnTerminate(auth::markEventsAsCommitted)
                .then(Mono.just(auth));
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}