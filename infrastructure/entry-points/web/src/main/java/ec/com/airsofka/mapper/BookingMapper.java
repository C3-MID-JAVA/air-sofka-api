package ec.com.airsofka.mapper;


import ec.com.airsofka.booking.commands.CreateBookingCommand;
import ec.com.airsofka.dto.BookingCreateDTO;

public class BookingMapper {
    public static CreateBookingCommand toCommand(final BookingCreateDTO booking) {
        return  new CreateBookingCommand(
                null,
                booking.getBookingStatus(),
                booking.getBookingPrice(),
                booking.getDiscount(),
                booking.getFlightId(),
                booking.getUserId(),
                booking.getPaymentMethod(),
                booking.getEmail(),
                booking.getPrefix(),
                booking.getPhoneNumber(),
                booking.getPassengers(),
                booking.getDepartureCity(),
                booking.getArrivalCity(),
                booking.getDepartureDate(),
                booking.getArrivalDate(),
                booking.getAirportTax(),
                booking.getAdditionalCharges(),
                booking.getFuelInsurance(),
                booking.getBookingFee(),
                booking.getTotalAmount(),
                booking.getTicketPrice(),
                booking.getKeyNotes()

        );
    }
}
