package ec.com.airsofka.booking.commands;

import ec.com.airsofka.generics.utils.Command;

import java.math.BigDecimal;
import java.util.List;

public class CreateBookingCommand  extends Command {
    private final String bookingStatus;
    private final BigDecimal bookingPrice;
    private final BigDecimal discount;
    private final String flightId;
    private final String userId;

    private  final String paymentMethod;

    private final String email;
    private final String prefix;
    private final String phoneNumber;
    private final List<CreatePassengerCommand> passengers;

    private final String departureCity;
    private final String arrivalCity;
    private final String departureDate;
    private final String arrivalDate;
    private final BigDecimal airportTax;
    private final BigDecimal additionalCharges;
    private final BigDecimal fuelInsurance;
    private final BigDecimal bookingFee;
    private final BigDecimal totalAmount;
    private final BigDecimal ticketPrice;
    private final String keyNotes;

    public CreateBookingCommand(String aggregateId, String bookingStatus, BigDecimal bookingPrice, BigDecimal discount, String flightId, String userId, String paymentMethod, String email, String prefix, String phoneNumber, List<CreatePassengerCommand> passengers, String departureCity, String arrivalCity, String departureDate, String arrivalDate, BigDecimal airportTax, BigDecimal additionalCharges, BigDecimal fuelInsurance, BigDecimal bookingFee, BigDecimal totalAmount, BigDecimal ticketPrice, String keyNotes) {
        super(aggregateId);
        this.bookingStatus = bookingStatus;
        this.bookingPrice = bookingPrice;
        this.discount = discount;
        this.flightId = flightId;
        this.userId = userId;
        this.paymentMethod = paymentMethod;
        this.email = email;
        this.prefix = prefix;
        this.phoneNumber = phoneNumber;
        this.passengers = passengers;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.airportTax = airportTax;
        this.additionalCharges = additionalCharges;
        this.fuelInsurance = fuelInsurance;
        this.bookingFee = bookingFee;
        this.totalAmount = totalAmount;
        this.ticketPrice = ticketPrice;
        this.keyNotes = keyNotes;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public BigDecimal getBookingPrice() {
        return bookingPrice;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public String getFlightId() {
        return flightId;
    }

    public String getUserId() {
        return userId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getEmail() {
        return email;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public List<CreatePassengerCommand> getPassengers() {
        return passengers;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public BigDecimal getAirportTax() {
        return airportTax;
    }

    public BigDecimal getAdditionalCharges() {
        return additionalCharges;
    }

    public BigDecimal getFuelInsurance() {
        return fuelInsurance;
    }

    public BigDecimal getBookingFee() {
        return bookingFee;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public String getKeyNotes() {
        return keyNotes;
    }
}
