package ec.com.airsofka;

import ec.com.airsofka.aggregate.auth.events.UserCreated;
import ec.com.airsofka.aggregate.auth.events.UserUpdated;
import ec.com.airsofka.aggregate.flightOperation.events.FlightCreated;
import ec.com.airsofka.aggregate.flightOperation.events.FlightUpdated;
import ec.com.airsofka.aggregate.flightOperation.events.SeatListCreated;
import ec.com.airsofka.aggregate.flightOperation.events.SeatReserved;
import ec.com.airsofka.aggregate.planeManagement.events.MaintenanceCreated;
import ec.com.airsofka.aggregate.planeManagement.events.PlaneCreated;
import ec.com.airsofka.aggregate.planeManagement.events.PlaneUpdated;
import ec.com.airsofka.aggregate.reservation.events.*;
import ec.com.airsofka.billing.queries.usecases.BillingSavedViewUseCase;
import ec.com.airsofka.booking.queries.usecases.BookingSavedViewUseCase;
import ec.com.airsofka.commands.SendEmailCommand;
import ec.com.airsofka.commands.usecases.SendEmailUseCase;
import ec.com.airsofka.config.RabbitProperties;
import ec.com.airsofka.contact.queries.usecases.ContactSavedViewUseCase;
import ec.com.airsofka.flight.queries.usecases.FlightSavedViewUseCase;
import ec.com.airsofka.flight.queries.usecases.FlightUpdateViewUseCase;
import ec.com.airsofka.gateway.BusEventListener;
import ec.com.airsofka.gateway.data.EmailData;
import ec.com.airsofka.gateway.dto.FlightDTO;
import ec.com.airsofka.gateway.dto.MaintenanceDTO;
import ec.com.airsofka.gateway.dto.PlaneDTO;
import ec.com.airsofka.gateway.dto.SeatDTO;
import ec.com.airsofka.gateway.dto.UserDTO;
import ec.com.airsofka.gateway.dto.*;
import ec.com.airsofka.generics.domain.DomainEvent;
import ec.com.airsofka.maintenance.queries.usecases.MaintenanceSavedViewUseCase;
import ec.com.airsofka.passenger.PassengerCreatedDTO;
import ec.com.airsofka.passenger.queries.usecases.PassengerSavedViewUseCase;
import ec.com.airsofka.plane.queries.usecases.PlaneSavedViewUseCase;
import ec.com.airsofka.seat.SeatCreatedDTO;
import ec.com.airsofka.seat.queries.usecases.SeatListSavedViewUseCase;
import ec.com.airsofka.user.queries.query.GetByElementQuery;
import ec.com.airsofka.user.queries.usecases.FrequentUserUseCase;
import ec.com.airsofka.user.queries.usecases.UserSavedViewUseCase;
import ec.com.airsofka.user.queries.usecases.UserUpdatedViewUseCase;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BusListener implements BusEventListener {
    private final RabbitProperties rabbitProperties;
    private final SendEmailUseCase sendEmailUseCase;
    private final FlightSavedViewUseCase flightSavedViewUseCase;
    private final FlightUpdateViewUseCase flightUpdateViewUseCase;
    private final UserSavedViewUseCase userSavedViewUseCase;
    private final UserUpdatedViewUseCase userUpdatedViewUseCase;
    private final PlaneSavedViewUseCase planeSavedViewUseCase;
    private final SeatListSavedViewUseCase seatListSavedViewUseCase;
    private final MaintenanceSavedViewUseCase maintenanceSavedViewUseCase;
    private final BillingSavedViewUseCase billingSavedViewUseCase;
    private final BookingSavedViewUseCase bookingSavedViewUseCase;
    private final ContactSavedViewUseCase contactSavedViewUseCase;
    private final PassengerSavedViewUseCase passengerSavedViewUseCase;
    private final FrequentUserUseCase frequentUserUseCase;

    public BusListener(
            RabbitProperties rabbitProperties,
            SendEmailUseCase sendEmailUseCase,
            FlightSavedViewUseCase flightSavedViewUseCase,
            FlightUpdateViewUseCase flightUpdateViewUseCase,
            UserSavedViewUseCase userSavedViewUseCase,
            UserUpdatedViewUseCase userUpdatedViewUseCase,
            PlaneSavedViewUseCase planeSavedViewUseCase,
            SeatListSavedViewUseCase seatListSavedViewUseCase,
            MaintenanceSavedViewUseCase maintenanceSavedViewUseCase,
            BillingSavedViewUseCase billingSavedViewUseCase,
            BookingSavedViewUseCase bookingSavedViewUseCase,
            ContactSavedViewUseCase contactSavedViewUseCase,
            PassengerSavedViewUseCase passengerSavedViewUseCase,
            FrequentUserUseCase frequentUserUseCase
    ) {
        this.rabbitProperties = rabbitProperties;
        this.sendEmailUseCase = sendEmailUseCase;
        this.flightSavedViewUseCase = flightSavedViewUseCase;
        this.flightUpdateViewUseCase = flightUpdateViewUseCase;
        this.userSavedViewUseCase = userSavedViewUseCase;
        this.userUpdatedViewUseCase = userUpdatedViewUseCase;
        this.planeSavedViewUseCase = planeSavedViewUseCase;
        this.seatListSavedViewUseCase = seatListSavedViewUseCase;
        this.maintenanceSavedViewUseCase = maintenanceSavedViewUseCase;
        this.billingSavedViewUseCase = billingSavedViewUseCase;
        this.bookingSavedViewUseCase = bookingSavedViewUseCase;
        this.contactSavedViewUseCase = contactSavedViewUseCase;
        this.passengerSavedViewUseCase = passengerSavedViewUseCase;
        this.frequentUserUseCase = frequentUserUseCase;
    }

    @Override
    @RabbitListener(queues = "#{rabbitProperties.getBookingQueue()}")
    public void receiveBookingCreated(DomainEvent event) {
        System.out.println("Booking created");
        BookingCreated booking = (BookingCreated) event;
        BookingDTO bookingDTO = new BookingDTO(
                booking.getId(),
                booking.getStatus(),
                booking.getTotalPrice(),
                booking.getDiscount(),
                booking.getFlightId(),
                booking.getUserId()
        );
        bookingSavedViewUseCase.accept(bookingDTO);

        if(booking.getUserId() != null && !booking.getUserId().isEmpty()){
            frequentUserUseCase.accept(new GetByElementQuery(booking.getUserId()));
        }
    }

    @Override
    @RabbitListener(queues = "#{rabbitProperties.getFlightCreatedQueue()}")
    public void receiveFlightCreated(DomainEvent flightCreated) {
        FlightCreated flight = (FlightCreated) flightCreated;

        FlightDTO flightDTO = new FlightDTO(
                flight.getId(),
                flight.getOrigin(),
                flight.getDestination(),
                flight.getDeparture(),
                flight.getArrival(),
                flight.getPrice(),
                flight.getIdPlane()
        );

        flightSavedViewUseCase.accept(flightDTO);
    }

    @Override
    @RabbitListener(queues = "#{rabbitProperties.getFlightUpdatedQueue()}")
    public void receiveFlightUpdated(DomainEvent flightUpdated) {
        FlightUpdated flight = (FlightUpdated) flightUpdated;

        FlightDTO flightDTO = new FlightDTO(
                flight.getId(),
                flight.getOrigin(),
                flight.getDestination(),
                flight.getDeparture(),
                flight.getArrival(),
                flight.getPrice(),
                flight.getIdPlane()
        );

        flightUpdateViewUseCase.accept(flightDTO);
    }

    @Override
    @RabbitListener(queues = "#{rabbitProperties.getUserCreatedQueue()}")
    public void receiveUserCreated(DomainEvent userCreated) {
        UserCreated user = (UserCreated) userCreated;

        UserDTO userDTO = new UserDTO(
                user.getUserId(),
                user.getBirthDate(),
                user.getDocumentNumber(),
                user.getDocumentType(),
                user.getEmail(),
                user.getFirstLastName(),
                user.getFrequent(),
                user.getLastLastName(),
                user.getName(),
                user.getNumberOfFlights(),
                user.getPassword(),
                user.getPhone(),
                user.getPrefix(),
                user.getRole(),
                user.getTitle()
        );
        userSavedViewUseCase.accept(userDTO);
    }

    @Override
    @RabbitListener(queues = "#{rabbitProperties.getUserUpdatedQueue()}")
    public void receiveUserUpdated(DomainEvent userUpdated) {
        UserUpdated user = (UserUpdated) userUpdated;

        UserDTO userDTO = new UserDTO(
                user.getUserId(),
                user.getBirthDate(),
                user.getDocumentNumber(),
                user.getDocumentType(),
                user.getEmail(),
                user.getFirstLastName(),
                user.getFrequent(),
                user.getLastLastName(),
                user.getName(),
                user.getNumberOfFlights(),
                user.getPassword(),
                user.getPhone(),
                user.getPrefix(),
                user.getRole(),
                user.getTitle()
        );
        userUpdatedViewUseCase.accept(userDTO);
    }


    @Override
    @RabbitListener(queues = "#{rabbitProperties.getPlaneCreatedQueue()}")
    public void receivePlaneCreated(DomainEvent planeCreated) {
        PlaneCreated plane = (PlaneCreated) planeCreated;

        PlaneDTO planeDTO = new PlaneDTO(
                plane.getId(),
                plane.getState(),
                plane.getModel()
        );

        planeSavedViewUseCase.accept(planeDTO);
    }

    @Override
    @RabbitListener(queues = "#{rabbitProperties.getMaintenanceQueue()}")
    public void receiveMaintenanceCreated(DomainEvent maintenanceCreated) {
        MaintenanceCreated maintenance = (MaintenanceCreated) maintenanceCreated;

        MaintenanceDTO maintenanceDTO = new MaintenanceDTO(
                maintenance.getId(),
                maintenance.getStart(),
                maintenance.getEnd(),
                maintenance.getIdPlane()
        );

        maintenanceSavedViewUseCase.accept(maintenanceDTO);
    }

    @Override
    @RabbitListener(queues = "#{rabbitProperties.getPlaneUpdatedQueue()}")
    public void receivePlaneUpdated(DomainEvent planeUpdated) {
        PlaneUpdated plane = (PlaneUpdated) planeUpdated;

        PlaneDTO planeDTO = new PlaneDTO(
                plane.getId(),
                plane.getState(),
                plane.getModel()
        );

        planeSavedViewUseCase.accept(planeDTO);
    }

    @Override
    @RabbitListener(queues = "#{rabbitProperties.getSeatCreatedQueue()}")
    public void receiveSeatCreated(DomainEvent seatCreated) {
        SeatListCreated seats = (SeatListCreated) seatCreated;

        for (SeatCreatedDTO seat : seats.getSeats()) {
            SeatDTO seatDTO = new SeatDTO(
                    seat.getId(),
                    seat.getNumber(),
                    seat.getRow(),
                    seat.getColumn(),
                    seat.getType(),
                    seat.getStatus(),
                    seat.getPrice(),
                    seat.getIdFlight()
            );

            seatListSavedViewUseCase.accept(seatDTO);
        }
    }

    @RabbitListener(queues = "#{rabbitProperties.getSeatReservedQueue()}")
    public void receiveSeatReserved(DomainEvent seatListUpdated) {
        SeatReserved seat = (SeatReserved) seatListUpdated;

        SeatDTO seatDTO = new SeatDTO(
                seat.getSeatId(),
                seat.getNumber(),
                seat.getRow(),
                seat.getColumn(),
                seat.getType(),
                seat.getStatus(),
                seat.getPrice(),
                seat.getIdFlight()
        );

        seatListSavedViewUseCase.accept(seatDTO);

    }


    @Override
    @RabbitListener(queues = "#{rabbitProperties.getEmailQueue()}")
    public void receiveMailEvent(EmailData emailData) {
        sendEmailUseCase.execute(new SendEmailCommand(emailData.getEmail(), emailData))
                .doOnSuccess(value -> System.out.println("Email sent to " + emailData.getEmail()))
                .subscribe();
    }

    @Override
    @RabbitListener(queues = "#{rabbitProperties.getBillingQueue()}")
    public void receiveBillingEvent(DomainEvent event) {
        BillingCreated billing = (BillingCreated) event;
        BillingDTO billingDTO = new BillingDTO(
                billing.getId(),
                billing.getPaymentMethod(),
                billing.getTotalPrice(),
                billing.getBookingId()
        );

        billingSavedViewUseCase.accept(billingDTO);


    }

    @Override
    @RabbitListener(queues = "#{rabbitProperties.getContactQueue()}")
    public void receiveContact(DomainEvent event) {
        ContactCreated contact = (ContactCreated) event;
        ContactDTO contactDTO = new ContactDTO(
                contact.getId(),
                contact.getEmail(),
                contact.getPrefix(),
                contact.getPhone(),
                contact.getBookingId()
        );
        contactSavedViewUseCase.accept(contactDTO);


    }

    @Override
    @RabbitListener(queues = "#{rabbitProperties.getPassengerQueue()}")
    public void receivePassenger(DomainEvent event) {
        PassengerListCreated passengers = (PassengerListCreated) event;
        for (PassengerCreatedDTO passenger : passengers.getPassengers()) {
            PassengerDTO passengerDTO = new PassengerDTO(
                    passenger.getId(),
                    passenger.getTitle(),
                    passenger.getName(),
                    passenger.getLastName(),
                    passenger.getPassengerType(),
                    passenger.getSeatId(),
                    passenger.getBookingId()
            );
            passengerSavedViewUseCase.accept(passengerDTO);


        }


    }

}

