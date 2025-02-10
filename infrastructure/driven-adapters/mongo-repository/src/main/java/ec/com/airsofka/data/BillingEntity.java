package ec.com.airsofka.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "billing")
public class BillingEntity {

    @Id
    private String id;

    private  String paymentMethod;
    private BigDecimal totalPrice;
    private  String idBooking;

    public BillingEntity() {
    }

    public BillingEntity(String id, String paymentMethod, BigDecimal totalPrice, String idBooking) {
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.totalPrice = totalPrice;
        this.idBooking = idBooking;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(String idBooking) {
        this.idBooking = idBooking;
    }
}

