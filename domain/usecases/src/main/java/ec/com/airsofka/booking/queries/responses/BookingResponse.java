package ec.com.airsofka.booking.queries.responses;

import java.math.BigDecimal;

public class BookingResponse {
    private final  String message;
    private final String email;
    private final String phoneNumber;
    private final BigDecimal total;

    public BookingResponse(String message, String email, String phoneNumber, BigDecimal total) {
        this.message = message;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.total = total;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
