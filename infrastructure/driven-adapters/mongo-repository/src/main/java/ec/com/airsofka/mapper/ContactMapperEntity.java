package ec.com.airsofka.mapper;

import ec.com.airsofka.data.ContactEntity;
import ec.com.airsofka.gateway.dto.ContactDTO;

public class ContactMapperEntity {
    public static ContactEntity toContactEntity(ContactDTO contactEntity) {
        return  new ContactEntity(
                contactEntity.getId(),
                contactEntity.getEmail(),
                contactEntity.getPrefix(),
                contactEntity.getPhone(),
                contactEntity.getBookingId()
        );
    }

    public static ContactDTO toContactDTO(ContactEntity contactEntity) {
        return new ContactDTO(
                contactEntity.getId(),
                contactEntity.getEmail(),
                contactEntity.getPrefix(),
                contactEntity.getPhone(),
                contactEntity.getIdBooking()
        );
    }

}
