package ec.com.airsofka.seat;

import ec.com.airsofka.aggregate.flightOperation.events.EventsFlighOperationEnum;

import java.math.BigDecimal;
import java.util.UUID;

public class SeatCreatedDTO {
    private String id;
    private String number;
    private Integer row;
    private String column;
    private SeatClass type;
    private SeatStatus status;
    private BigDecimal price;
    private String idFlight;


    public SeatCreatedDTO() {
    }

    public SeatCreatedDTO(String id, String number, Integer row, String column, SeatClass type, SeatStatus status, BigDecimal price, String idFlight) {
        this.id = id;
        this.number = number;
        this.row = row;
        this.column = column;
        this.type = type;
        this.status = status;
        this.price = price;
        this.idFlight = idFlight;
    }

    public String getId() {
        return id;
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
