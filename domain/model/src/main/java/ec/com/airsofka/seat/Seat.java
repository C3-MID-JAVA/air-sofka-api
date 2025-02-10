package ec.com.airsofka.seat;

import ec.com.airsofka.flight.values.FlightId;
import ec.com.airsofka.generics.utils.Entity;
import ec.com.airsofka.seat.values.SeatId;
import ec.com.airsofka.seat.values.objects.*;
import ec.com.airsofka.seat.values.objects.Number;

public class Seat extends Entity<SeatId> {
    private final Number number;
    private final Row row;
    private final Column column;
    private final Type type;
    private final Status status;
    private final Price price;
    private final FlightId idFlight;

    public Seat(SeatId id, Number number, Row row, Column column, Type type, Status status, Price price, FlightId idFlight) {
        super(id);
        this.number = number; /*generateSeatNumber(row, column);*/
        this.row = row;
        this.column = column;
        this.type = type;
        this.status = status;
        this.price = price;
        this.idFlight = idFlight;
    }

    private String generateSeatNumber(Integer row, String column){
        return row + column;
    }

    public Number getNumber() {
        return number;
    }

    public Row getRow() {
        return row;
    }

    public Column getColumn() {
        return column;
    }

/*    public void setRow(Integer row) {
        this.row = row;
        this.number = generateSeatNumber(row, this.column);
    }

    public void setColumn(String column) {
        this.column = column;
        this.number = generateSeatNumber(this.row, column);
    }*/

    public Type getType() {
        return type;
    }

    public Status getStatus() {
        return status;
    }

    public Price getPrice() {
        return price;
    }

    public FlightId getIdFlight() {
        return idFlight;
    }
}
