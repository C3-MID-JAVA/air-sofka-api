package ec.com.airsofka.mapper;

import ec.com.airsofka.data.UserEntity;
import ec.com.airsofka.gateway.dto.UserDTO;

public class UserMapperEntity {

    public static UserEntity toEntity(UserDTO userDTO) {
        return new UserEntity(
                userDTO.getUserId(),
                userDTO.getBirthDate(),
                userDTO.getDocumentNumber(),
                userDTO.getDocumentType(),
                userDTO.getEmail(),
                userDTO.getFirstLastName(),
                userDTO.getFrequent(),
                userDTO.getLastLastName(),
                userDTO.getName(),
                userDTO.getNumberOfFlights(),
                userDTO.getPassword(),
                userDTO.getPhone(),
                userDTO.getPrefix(),
                userDTO.getRole(),
                userDTO.getTitle()
        );
    }

    public static UserDTO fromEntity(UserEntity userEntity) {
        return new UserDTO(
                userEntity.getId(),
                userEntity.getBirthDate(),
                userEntity.getDocumentNumber(),
                userEntity.getDocumentType(),
                userEntity.getEmail(),
                userEntity.getFirstLastName(),
                userEntity.getFrequent(),
                userEntity.getLastLastName(),
                userEntity.getName(),
                userEntity.getNumberOfFlights(),
                userEntity.getPassword(),
                userEntity.getPhone(),
                userEntity.getPrefix(),
                userEntity.getRole(),
                userEntity.getTitle()
        );
    }

}
