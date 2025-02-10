package ec.com.airsofka.gateway.data;

import ec.com.airsofka.booking.commands.CreatePassengerCommand;
import ec.com.airsofka.gateway.dto.PassengerDTO;

import java.math.BigDecimal;
import java.util.List;

public class EmailData {
    private final String email;
    private final String phoneNumber;
    private final String passengerName;
    private final String departureCity;
    private final String arrivalCity;
    private final String departureDate;
    private final String arrivalDate;

    private final BigDecimal ticketPrice;
    private final BigDecimal airportTax;
    private final BigDecimal additionalCharges;
    private final BigDecimal fuelInsurance;
    private final BigDecimal bookingFee;
    private final BigDecimal totalAmount;

    private final String keyNotes;
    private final List<CreatePassengerCommand> passengers;


    public EmailData(String email, String phoneNumber, String passengerName, String departureCity, String arrivalCity, String departureDate, String arrivalDate, BigDecimal ticketPrice, BigDecimal airportTax, BigDecimal additionalCharges, BigDecimal fuelInsurance, BigDecimal bookingFee, BigDecimal totalAmount, String keyNotes, List<CreatePassengerCommand> passengers) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passengerName = passengerName;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.ticketPrice = ticketPrice;
        this.airportTax = airportTax;
        this.additionalCharges = additionalCharges;
        this.fuelInsurance = fuelInsurance;
        this.bookingFee = bookingFee;
        this.totalAmount = totalAmount;
        this.keyNotes = keyNotes;
        this.passengers = passengers;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassengerName() {
        return passengerName;
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

    public BigDecimal getTicketPrice() {
        return ticketPrice;
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

    public String getKeyNotes() {
        return keyNotes;
    }

    public List<CreatePassengerCommand> getPassengers() {
        return passengers;
    }
}
