package ec.com.airsofka.gateway.dto;

import java.math.BigDecimal;

public class BookingDTO {
    private  String id;
    private  String status;
    private  BigDecimal totalPrice;
    private  BigDecimal discount;
    private  String flightId;
    private  String userId;

    public BookingDTO(String id, String status, BigDecimal totalPrice, BigDecimal discount, String flightId, String userId) {
        this.id = id;
        this.status = status;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.flightId = flightId;
        this.userId = userId;
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

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
