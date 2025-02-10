package ec.com.airsofka.gateway;

import ec.com.airsofka.gateway.dto.BookingDTO;
import reactor.core.publisher.Mono;

public interface IBookingRepository {
    Mono<BookingDTO> save(BookingDTO bookingDTO);
}
