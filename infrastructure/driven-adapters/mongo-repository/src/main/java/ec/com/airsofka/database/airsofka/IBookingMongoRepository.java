package ec.com.airsofka.database.airsofka;

import ec.com.airsofka.data.BookingEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IBookingMongoRepository  extends ReactiveMongoRepository<BookingEntity, String> {
}
