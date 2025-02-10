package ec.com.airsofka.commands.maintenance;

import ec.com.airsofka.aggregate.planeManagement.events.EventsPlaneManagementEnum;
import ec.com.airsofka.aggregate.planeManagement.events.PlaneCreated;
import ec.com.airsofka.gateway.BusEvent;
import ec.com.airsofka.gateway.IEventStore;
import ec.com.airsofka.generics.domain.DomainEvent;
import ec.com.airsofka.maintenance.commands.CreateMaintenanceCommand;
import ec.com.airsofka.maintenance.commands.usecases.CreateMaintenanceUseCase;
import ec.com.airsofka.plane.PlaneStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateMaintenanceUseCaseTest {
    @Mock
    private IEventStore repository;

    @Mock
    private BusEvent busEvent;

    @InjectMocks
    private CreateMaintenanceUseCase useCase;

    @Test
    void shouldCreateMaintenanceSuccessfully() {
        String idPlane = UUID.randomUUID().toString();
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = LocalDateTime.now().plusDays(7);
        CreateMaintenanceCommand command = new CreateMaintenanceCommand(start, end, idPlane);

        String aggregateRootId = UUID.randomUUID().toString();
        PlaneCreated planeCreatedEvent = new PlaneCreated(idPlane, PlaneStatus.ENABLED, "Boeing 747");
        planeCreatedEvent.setAggregateRootId(aggregateRootId);

        when(repository.findAllAggregateByEvent("planeManagement", EventsPlaneManagementEnum.PLANE_CREATED.name()))
                .thenReturn(Flux.just(planeCreatedEvent));

        when(repository.findAggregate(aggregateRootId, "planeManagement")).thenReturn(Flux.empty());

        when(repository.save(any(DomainEvent.class))).thenReturn(Mono.empty());
        doNothing().when(busEvent).sendEventMaintenanceCreated(any());

        StepVerifier.create(useCase.execute(command))
                .consumeNextWith(response -> {
                    assertNotNull(response.getId());
                    assertEquals(start, response.getStart());
                    assertEquals(end, response.getEnd());
                    assertEquals(idPlane, response.getIdPlane());
                })
                .verifyComplete();

        verify(repository, times(1)).findAllAggregateByEvent("planeManagement", EventsPlaneManagementEnum.PLANE_CREATED.name());
        verify(repository, times(1)).findAggregate(aggregateRootId, "planeManagement");
        verify(repository, times(1)).save(any(DomainEvent.class));
        verify(busEvent, times(1)).sendEventMaintenanceCreated(any());
    }

}
