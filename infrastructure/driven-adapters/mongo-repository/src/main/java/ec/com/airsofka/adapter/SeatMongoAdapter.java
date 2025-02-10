package ec.com.airsofka.adapter;

import ec.com.airsofka.data.SeatEntity;
import ec.com.airsofka.database.airsofka.SeatMongoRepository;
import ec.com.airsofka.gateway.SeatRepository;
import ec.com.airsofka.gateway.dto.SeatDTO;
import ec.com.airsofka.mapper.SeatMapperEntity;
import ec.com.airsofka.seat.Seat;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;

@Repository
public class SeatMongoAdapter implements SeatRepository {

    private final SeatMongoRepository repository;
    private final ReactiveMongoTemplate airMongoTemplate;

    public SeatMongoAdapter(SeatMongoRepository repository, @Qualifier("airMongoTemplate") ReactiveMongoTemplate airMongoTemplate) {
        this.repository = repository;
        this.airMongoTemplate = airMongoTemplate;
    }

    @Override
    public Mono<SeatDTO> save(SeatDTO seatDTO) {
        SeatEntity seatEntity = SeatMapperEntity.toEntity(seatDTO);
        return repository.save(seatEntity).map(SeatMapperEntity::fromEntity);
    }

    @Override
    public Flux<SeatDTO> getAll() {
        return repository.findAll().map(SeatMapperEntity::fromEntity);
    }

    @Override
    public Flux<SeatDTO> getAllByFlightId(String id) {
        return repository.findAllByIdFlight(id)
                .sort(Comparator.comparing(SeatEntity::getRow)
                        .thenComparing(SeatEntity::getColumn))
                .map(SeatMapperEntity::fromEntity);
    }

    @Override
    public Mono<SeatDTO> getById(String id) {
        return repository.findById(id).map(SeatMapperEntity::fromEntity);
    }
}
