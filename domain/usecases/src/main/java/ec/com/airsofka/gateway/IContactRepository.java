package ec.com.airsofka.gateway;


import ec.com.airsofka.gateway.dto.ContactDTO;
import reactor.core.publisher.Mono;

public interface IContactRepository {
    Mono<ContactDTO> save(ContactDTO contactDTO);
}
