package ec.com.airsofka.adapter;

import ec.com.airsofka.database.airsofka.IContactMongoRepository;
import ec.com.airsofka.gateway.IContactRepository;
import ec.com.airsofka.gateway.dto.ContactDTO;
import ec.com.airsofka.mapper.ContactMapperEntity;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class ContactMongoAdapter implements IContactRepository {
    private final ReactiveMongoTemplate airMongoTemplate;
    private  final IContactMongoRepository contactMongoRepository;

    public ContactMongoAdapter(ReactiveMongoTemplate airMongoTemplate, IContactMongoRepository contactMongoRepository) {
        this.airMongoTemplate = airMongoTemplate;
        this.contactMongoRepository = contactMongoRepository;
    }

    @Override
    public Mono<ContactDTO> save(ContactDTO contactDTO) {
        return contactMongoRepository.save(ContactMapperEntity.toContactEntity(contactDTO))
                .map(ContactMapperEntity::toContactDTO);
    }
}
