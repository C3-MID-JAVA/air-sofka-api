package ec.com.airsofka.aggregate.auth;

import ec.com.airsofka.aggregate.auth.events.UserCreated;
import ec.com.airsofka.aggregate.auth.events.UserUpdated;
import ec.com.airsofka.generics.domain.DomainActionsContainer;
import ec.com.airsofka.user.User;
import ec.com.airsofka.user.values.UserId;
import ec.com.airsofka.user.values.objects.*;

public class UserHandler extends DomainActionsContainer {

    public UserHandler(Auth authAggregate) {
        addDomainActions((UserCreated event) -> {
            User user = new User(
                    UserId.of(event.getUserId()),
                    BirthDate.of(event.getBirthDate()),
                    DocumentNumber.of(event.getDocumentNumber()),
                    event.getDocumentType(),
                    Email.of(event.getEmail()),
                    FirstLastName.of(event.getFirstLastName()),
                    Frequent.of(event.getFrequent()),
                    LastLastName.of(event.getLastLastName()),
                    Name.of(event.getName()),
                    NumberOfFlights.of(event.getNumberOfFlights()),
                    Password.of(event.getPassword()),
                    Phone.of(event.getPhone()),
                    Prefix.of(event.getPrefix()),
                    event.getRole(),
                    Title.of(event.getTitle()));

            authAggregate.setUser(user);
        });

        addDomainActions((UserUpdated event) -> {
            User user = new User(
                    UserId.of(event.getUserId()),
                    BirthDate.of(event.getBirthDate()),
                    DocumentNumber.of(event.getDocumentNumber()),
                    event.getDocumentType(),
                    Email.of(event.getEmail()),
                    FirstLastName.of(event.getFirstLastName()),
                    Frequent.of(event.getFrequent()),
                    LastLastName.of(event.getLastLastName()),
                    Name.of(event.getName()),
                    NumberOfFlights.of(event.getNumberOfFlights()),
                    Password.of(event.getPassword()),
                    Phone.of(event.getPhone()),
                    Prefix.of(event.getPrefix()),
                    event.getRole(),
                    Title.of(event.getTitle()));

            authAggregate.setUser(user);
        });
    }
}
