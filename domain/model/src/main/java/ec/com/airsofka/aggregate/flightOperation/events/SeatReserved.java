package ec.com.airsofka.aggregate.flightOperation.events;

import ec.com.airsofka.generics.domain.DomainEvent;
import ec.com.airsofka.seat.SeatClass;
import ec.com.airsofka.seat.SeatStatus;

import java.math.BigDecimal;


public class SeatReserved extends DomainEvent {
    private String seatId;
    private String number;
    private Integer row;
    private String column;
    private SeatClass type;
    private SeatStatus status;
    private BigDecimal price;
    private String idFlight;

    public SeatReserved() {
        super(EventsFlighOperationEnum.SEAT_RESERVED.name());
    }

    public SeatReserved(String seatId, String number, Integer row, String column, SeatClass type, SeatStatus status, BigDecimal price, String idFlight) {
        super(EventsFlighOperationEnum.SEAT_RESERVED.name());
        this.seatId = seatId;
        this.number = number;
        this.row = row;
        this.column = column;
        this.type = type;
        this.status = status;
        this.price = price;
        this.idFlight = idFlight;
    }


    public String getSeatId() {
        return seatId;
    }

    public String getNumber() {
        return number;
    }

    public Integer getRow() {
        return row;
    }

    public String getColumn() {
        return column;
    }

    public SeatClass getType() {
        return type;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getIdFlight() {
        return idFlight;
    }
}
