package ec.com.airsofka.seat.queries.responses;

import ec.com.airsofka.seat.SeatClass;
import ec.com.airsofka.seat.SeatStatus;

import java.math.BigDecimal;

public class SeatResponse {
    private final String id;
    private final String number;
    private final Integer row;
    private final String column;
    private final SeatClass type;
    private final SeatStatus status;
    private final BigDecimal price;
    private final String idFlight;

    public SeatResponse(String id, String number, Integer row, String column, SeatClass type, SeatStatus status, BigDecimal price, String idFlight) {
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
