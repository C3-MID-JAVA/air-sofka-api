package ec.com.airsofka.gateway;


import ec.com.airsofka.gateway.data.EmailData;
import ec.com.airsofka.generics.domain.DomainEvent;

public interface BusEventListener {

    void receiveBookingCreated(DomainEvent emailDetails);
    void receiveFlightCreated(DomainEvent flightCreated);
    void receiveFlightUpdated(DomainEvent flightUpdated);
    void receivePlaneCreated(DomainEvent planeCreated);
    void receiveSeatReserved(DomainEvent seatListUpdated);
    void receiveUserCreated(DomainEvent userCreated);
    void receiveUserUpdated(DomainEvent userUpdated);
    void receiveMaintenanceCreated(DomainEvent maintenanceCreated);
    void receivePlaneUpdated(DomainEvent planeUpdated);
    void receiveSeatCreated(DomainEvent seatCreated);

    void receiveMailEvent(EmailData emailData);
    void receiveBillingEvent(DomainEvent mailEvent);
    void receiveContact(DomainEvent mailEvent);
    void receivePassenger(DomainEvent mailEvent);

}
