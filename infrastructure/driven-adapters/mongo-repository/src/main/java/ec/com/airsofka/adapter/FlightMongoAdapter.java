package ec.com.airsofka.adapter;

import ec.com.airsofka.data.FlightEntity;
import ec.com.airsofka.database.airsofka.FlightMongoRepository;
import ec.com.airsofka.flight.queries.query.GetAllFlightQuery;
import ec.com.airsofka.gateway.IFlightRepository;
import ec.com.airsofka.gateway.dto.FlightDTO;
import ec.com.airsofka.mapper.FlightMapperEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.*;
import java.util.Date;

@Repository
public class FlightMongoAdapter implements IFlightRepository {

    private final FlightMongoRepository repository;
    private final ReactiveMongoTemplate airMongoTemplate;

    public FlightMongoAdapter(FlightMongoRepository repository, @Qualifier("airMongoTemplate") ReactiveMongoTemplate airMongoTemplate) {
        this.repository = repository;
        this.airMongoTemplate = airMongoTemplate;
    }

    @Override
    public Mono<FlightDTO> save(FlightDTO flightDTO) {
        FlightEntity flightEntity = FlightMapperEntity.toEntity(flightDTO);
        return repository.save(flightEntity).map(FlightMapperEntity::fromEntity);
    }

    @Override
    public Flux<FlightDTO> getAll(GetAllFlightQuery filters) {
        Criteria criteria = new Criteria();

        if (filters.getOrigin() != null) {
            criteria = criteria.and("origin").is(filters.getOrigin());
        }

        if (filters.getDestination() != null) {
            criteria = criteria.and("destination").is(filters.getDestination());
        }

        if (filters.getDepartureDate() != null) {
            LocalDate searchDate = LocalDate.parse(filters.getDepartureDate());
            ZoneOffset zoneOffset = ZoneId.systemDefault().getRules().getOffset(Instant.now());
            Instant startOfDay = searchDate.atStartOfDay(zoneOffset).toInstant();
            Instant endOfDay = searchDate.atTime(23, 59, 59).atZone(zoneOffset).toInstant();

            criteria = criteria.and("departure").gte(startOfDay).lte(endOfDay);
        }

        Query query = Query.query(criteria);
        return airMongoTemplate.find(query, FlightEntity.class).map(FlightMapperEntity::fromEntity);
    }
}
