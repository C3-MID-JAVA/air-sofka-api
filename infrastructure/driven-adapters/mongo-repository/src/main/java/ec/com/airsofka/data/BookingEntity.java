package ec.com.airsofka.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "booking")
public class BookingEntity {

    @Id
    private  String id;

    private  String status;
    private BigDecimal totalPrice;
    private  BigDecimal discount;
    private  String idFlight;
    private  String idUser;

    public BookingEntity() {
    }

    public BookingEntity(String id, String status, BigDecimal totalPrice, BigDecimal discount, String idFlight, String idUser) {
        this.id = id;
        this.status = status;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.idFlight = idFlight;
        this.idUser = idUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(String idFlight) {
        this.idFlight = idFlight;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
