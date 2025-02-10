package ec.com.airsofka.mapper;


import ec.com.airsofka.data.BookingEntity;
import ec.com.airsofka.gateway.dto.BookingDTO;

public class BookingMapperEntity {
    public  static BookingEntity toBookingEntity(BookingDTO booking) {
        return new BookingEntity(
                booking.getId(),
                booking.getStatus(),
                booking.getTotalPrice(),
                booking.getDiscount(),
                booking.getFlightId(),
                booking.getUserId()
        );
    }

    public  static BookingDTO toBookingDTO(BookingEntity booking) {
        return new BookingDTO(
                booking.getId(),
                booking.getStatus(),
                booking.getTotalPrice(),
                booking.getDiscount(),
                booking.getIdFlight(),
                booking.getIdUser()
        );
    }
}
