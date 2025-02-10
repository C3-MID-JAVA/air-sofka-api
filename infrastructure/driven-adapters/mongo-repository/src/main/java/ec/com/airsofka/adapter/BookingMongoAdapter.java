package ec.com.airsofka.adapter;

import ec.com.airsofka.database.airsofka.IBookingMongoRepository;
import ec.com.airsofka.gateway.IBookingRepository;
import ec.com.airsofka.gateway.dto.BookingDTO;
import ec.com.airsofka.mapper.BookingMapperEntity;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class BookingMongoAdapter implements IBookingRepository {
    private  final IBookingMongoRepository bookingMongoRepository;
    private final ReactiveMongoTemplate airMongoTemplate;

    public BookingMongoAdapter(IBookingMongoRepository bookingMongoRepository, ReactiveMongoTemplate airMongoTemplate) {
        this.bookingMongoRepository = bookingMongoRepository;
        this.airMongoTemplate = airMongoTemplate;
    }

    @Override
    public Mono<BookingDTO> save(BookingDTO bookingDTO) {
        System.out.println("CALLING BOOKING");
        return bookingMongoRepository.save(BookingMapperEntity.toBookingEntity(bookingDTO))
                .map(BookingMapperEntity::toBookingDTO);
    }
}
