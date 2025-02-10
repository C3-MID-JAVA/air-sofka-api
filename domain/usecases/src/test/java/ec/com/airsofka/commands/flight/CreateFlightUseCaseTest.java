package ec.com.airsofka.commands.flight;

import ec.com.airsofka.flight.commands.CreateFlightCommand;
import ec.com.airsofka.flight.commands.usecases.CreateFlightUseCase;
import ec.com.airsofka.gateway.BusEvent;
import ec.com.airsofka.gateway.IEventStore;
import ec.com.airsofka.generics.domain.DomainEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateFlightUseCaseTest {

    @Mock
    private IEventStore repository;

    @Mock
    private BusEvent busEvent;

    @InjectMocks
    private CreateFlightUseCase useCase;

    @Test
    void shouldCreateFlightSuccessfully() {
        String origin = "New York";
        String destination = "London";
        LocalDateTime departure = LocalDateTime.now().plusHours(2);
        LocalDateTime arrival = LocalDateTime.now().plusHours(10);
        Double price = 500.0;
        String idPlane = UUID.randomUUID().toString();

        CreateFlightCommand command = new CreateFlightCommand(origin, destination, departure, arrival, price, idPlane);

        when(repository.save(any(DomainEvent.class))).thenReturn(Mono.empty());
        doNothing().when(busEvent).sendEventFlightCreated(any());
        doNothing().when(busEvent).sendEventSeatListCreated(any());


        StepVerifier.create(useCase.execute(command))
                .consumeNextWith(response -> {
                    assertNotNull(response.getId());
                    assertEquals(origin, response.getOrigin());
                    assertEquals(destination, response.getDestination());
                    assertEquals(departure, response.getDeparture());
                    assertEquals(arrival, response.getArrival());
                    assertEquals(price, response.getPrice());
                    assertEquals(idPlane, response.getIdPlane());
                })
                .verifyComplete();

        verify(repository, atLeast(2)).save(any(DomainEvent.class));
        verify(busEvent, times(1)).sendEventFlightCreated(any());
        verify(busEvent, times(1)).sendEventSeatListCreated(any());
    }
}
