package ec.com.airsofka.commands.usecases;


import ec.com.airsofka.commands.SendEmailCommand;
import ec.com.airsofka.data.SeatRecord;
import ec.com.airsofka.gateway.data.EmailData;
import ec.com.airsofka.generics.interfaces.IUseCaseExecute;
import ec.com.airsofka.queries.responses.EmailResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SendEmailUseCase implements IUseCaseExecute<SendEmailCommand, EmailResponse> {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public SendEmailUseCase(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public Mono<EmailResponse> execute(SendEmailCommand cmd) {
        return Mono.fromRunnable(() -> {
            try {
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);


                Context context = new Context();
                context.setVariables(buildVariables(cmd.getEmailData()));
                String htmlContent = templateEngine.process("booking-confirmation", context);

                helper.setTo(cmd.getTo());
                helper.setSubject("Your Flight Booking Confirmation");
                helper.setText(htmlContent, true);

                mailSender.send(message);
            } catch (MessagingException e) {
                throw new RuntimeException("Error sending email", e);
            }
        });
    }

    private Map<String, Object> buildVariables(EmailData emailData) {
        Map<String, Object> variables = new HashMap<>();
        LocalDate today = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy", Locale.ENGLISH);
        String formattedDate = today.format(formatter);

        variables.put("confirmationTitle", "Booking Confirmation");
        variables.put("bookingDate", formattedDate);

        variables.put("email", emailData.getEmail());
        variables.put("phoneNumber", emailData.getPhoneNumber());
        variables.put("passengerName", emailData.getPassengerName());
        variables.put("departureCity", emailData.getDepartureCity());
        variables.put("arrivalCity", emailData.getArrivalCity());
        variables.put("airline", "Air-Sofka");
        variables.put("departureDate", emailData.getDepartureDate());
        variables.put("arrivalDate", emailData.getArrivalDate());
        variables.put("flightNumber", "SH1234");
        //Taxes
        variables.put("ticketPrice", emailData.getTicketPrice());
        variables.put("airportTax", emailData.getAirportTax());
        variables.put("additionalCharges", emailData.getAdditionalCharges());
        variables.put("fuelInsurance", emailData.getFuelInsurance());
        variables.put("bookingFee", emailData.getBookingFee());
        variables.put("totalAmount", emailData.getTotalAmount());

        variables.put("keyNotes", emailData.getKeyNotes());

        List<SeatRecord> availableSeats = emailData.getPassengers()
                .stream()
                .map(passenger  -> new SeatRecord(
                        "ECONOMIC", passenger.getSeatId(),passenger.getPassengerName()
                ))
                .toList();

        List<Map<String, String>> seats = availableSeats.stream()
                .map(seat -> {
                    Map<String, String> seatData = new HashMap<>();
                    seatData.put("flightClass", seat.flightClass());
                    seatData.put("seatNumber", seat.seatNumber());
                    seatData.put("passengerName", seat.passengerName());
                    return seatData;
                })
                .toList();

        variables.put("seats", seats);
        return variables;

    }
}

