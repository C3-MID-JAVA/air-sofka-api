package ec.com.airsofka.booking.commands;

import ec.com.airsofka.generics.utils.Command;

public class CreatePassengerCommand extends Command {

    private final String passengerTitle;
    private final String passengerName;
    private final String  passengerLastName;
    private final String passengerType;
    private final  String seatId;

    public CreatePassengerCommand(String aggregateId, String passengerTitle, String passengerName, String passengerLastName, String passengerType, String seatId) {
        super(aggregateId);
        this.passengerTitle = passengerTitle;
        this.passengerName = passengerName;
        this.passengerLastName = passengerLastName;
        this.passengerType = passengerType;
        this.seatId = seatId;
    }

    public String getPassengerTitle() {
        return passengerTitle;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getPassengerLastName() {
        return passengerLastName;
    }

    public String getPassengerType() {
        return passengerType;
    }

    public String getSeatId() {
        return seatId;
    }
}
