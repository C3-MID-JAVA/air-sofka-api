package ec.com.airsofka.commands.plane;

import ec.com.airsofka.gateway.BusEvent;
import ec.com.airsofka.gateway.IEventStore;
import ec.com.airsofka.generics.domain.DomainEvent;
import ec.com.airsofka.plane.PlaneStatus;
import ec.com.airsofka.plane.commands.CreatePlaneCommand;
import ec.com.airsofka.plane.commands.usecases.CreatePlaneUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreatePlaneUseCaseTest {
    @Mock
    private IEventStore repository;

    @Mock
    private BusEvent busEvent;

    @InjectMocks
    private CreatePlaneUseCase useCase;

    @Test
    void shouldCreatePlaneSuccessfully() {
        String model = "Boeing 747";
        CreatePlaneCommand command = new CreatePlaneCommand(model);

        when(repository.save(any(DomainEvent.class))).thenReturn(Mono.empty());
        doNothing().when(busEvent).sendEventPlaneCreated(any());

        StepVerifier.create(useCase.execute(command))
                .consumeNextWith(response -> {
                    assertNotNull(response.getId());
                    assertEquals(PlaneStatus.ENABLED, response.getState());
                    assertEquals(model, response.getModel());
                })
                .verifyComplete();

        verify(repository, times(1)).save(any(DomainEvent.class));
        verify(busEvent, times(1)).sendEventPlaneCreated(any());
    }
}
