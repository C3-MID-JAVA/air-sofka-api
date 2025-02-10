package ec.com.airsofka.mapper;


import ec.com.airsofka.data.BillingEntity;
import ec.com.airsofka.gateway.dto.BillingDTO;

public class BillingMapperEntity {

    public static BillingEntity toBillingEntity(BillingDTO data) {
        return new BillingEntity(
                data.getId(),
                data.getPaymentMethod(),
                data.getTotalPrice(),
                data.getBookingId()
        );
    }
    public static BillingDTO toBillingDTO(BillingEntity entity) {
        return new BillingDTO(
                entity.getId(),
                entity.getPaymentMethod(),
                entity.getTotalPrice(),
                entity.getIdBooking()
        );
    }
}
