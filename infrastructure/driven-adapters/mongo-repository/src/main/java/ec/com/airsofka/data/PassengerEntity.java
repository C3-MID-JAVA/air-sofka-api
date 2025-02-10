package ec.com.airsofka.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "passenger")
public class PassengerEntity {

    @Id
    private String id;

    private  String title;
    private  String name;
    private  String lastName;
    private  String passengerType;
    private  String seatId;
    private  String idBooking;

    public PassengerEntity() {
    }

    public PassengerEntity(String id, String title, String name, String lastName, String passengerType, String seatId, String idBooking) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.lastName = lastName;
        this.passengerType = passengerType;
        this.seatId = seatId;
        this.idBooking = idBooking;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(String passengerType) {
        this.passengerType = passengerType;
    }

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public String getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(String idBooking) {
        this.idBooking = idBooking;
    }
}
