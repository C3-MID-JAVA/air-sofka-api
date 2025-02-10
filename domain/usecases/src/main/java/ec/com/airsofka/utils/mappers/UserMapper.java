package ec.com.airsofka.utils.mappers;

import ec.com.airsofka.gateway.dto.UserDTO;
import ec.com.airsofka.user.commands.UpdateUserCommand;

public class UserMapper {

    public static UpdateUserCommand toUpdateUserCommand(UserDTO userDTO) {
        return new UpdateUserCommand(
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

}
