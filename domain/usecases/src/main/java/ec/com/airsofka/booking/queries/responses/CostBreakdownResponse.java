package ec.com.airsofka.booking.queries.responses;

import java.math.BigDecimal;

public class CostBreakdownResponse {
    private final BigDecimal ticketPrice;
    private final BigDecimal airportTax;
    private final BigDecimal totalAmount;
    private final BigDecimal bookingFee;
    private final BigDecimal fuelInsurance;
    private final BigDecimal additionalCharges;
    private final BigDecimal discount;

    public CostBreakdownResponse(BigDecimal ticketPrice, BigDecimal airportTax, BigDecimal totalAmount, BigDecimal bookingFee, BigDecimal fuelInsurance, BigDecimal additionalCharges, BigDecimal discount) {
        this.ticketPrice = ticketPrice;
        this.airportTax = airportTax;
        this.totalAmount = totalAmount;
        this.bookingFee = bookingFee;
        this.fuelInsurance = fuelInsurance;
        this.additionalCharges = additionalCharges;
        this.discount = discount;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public BigDecimal getAirportTax() {
        return airportTax;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public BigDecimal getBookingFee() {
        return bookingFee;
    }

    public BigDecimal getFuelInsurance() {
        return fuelInsurance;
    }

    public BigDecimal getAdditionalCharges() {
        return additionalCharges;
    }

    public BigDecimal getDiscount() {
        return discount;
    }
}
