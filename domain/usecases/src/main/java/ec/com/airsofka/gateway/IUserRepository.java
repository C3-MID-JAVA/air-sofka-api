package ec.com.airsofka.gateway;

import ec.com.airsofka.gateway.dto.UserDTO;
import reactor.core.publisher.Mono;

public interface IUserRepository {
    Mono<UserDTO> save(UserDTO usertDTO);
    Mono<UserDTO> findById(String id);
    Mono<UserDTO> update(UserDTO usertDTO);
    Mono<UserDTO> findByEmail(String email);
}
