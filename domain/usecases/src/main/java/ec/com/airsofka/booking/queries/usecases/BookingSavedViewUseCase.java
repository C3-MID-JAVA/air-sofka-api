package ec.com.airsofka.booking.queries.usecases;

import ec.com.airsofka.gateway.IBookingRepository;
import ec.com.airsofka.gateway.dto.BookingDTO;
import ec.com.airsofka.generics.interfaces.IUseCaseAccept;

public class BookingSavedViewUseCase  implements IUseCaseAccept<BookingDTO, Void> {
    private final IBookingRepository bookingRepository;


    public BookingSavedViewUseCase(IBookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void accept(BookingDTO request) {
        bookingRepository.save(request).subscribe();
    }
}
