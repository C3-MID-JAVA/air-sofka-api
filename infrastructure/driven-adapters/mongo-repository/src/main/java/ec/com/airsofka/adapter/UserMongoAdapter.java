package ec.com.airsofka.adapter;

import ec.com.airsofka.data.UserEntity;
import ec.com.airsofka.database.airsofka.IUserMongoRepository;
import ec.com.airsofka.gateway.IUserRepository;
import ec.com.airsofka.gateway.dto.UserDTO;
import ec.com.airsofka.mapper.UserMapperEntity;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class UserMongoAdapter  implements IUserRepository {

    private final IUserMongoRepository userMongoRepository;
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public UserMongoAdapter(IUserMongoRepository userMongoRepository, ReactiveMongoTemplate reactiveMongoTemplate) {
        this.userMongoRepository = userMongoRepository;
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }


    @Override
    public Mono<UserDTO> save(UserDTO usertDTO) {
        UserEntity userEntity = UserMapperEntity.toEntity(usertDTO);
        return userMongoRepository.save(userEntity).map(UserMapperEntity::fromEntity);
    }

    @Override
    public Mono<UserDTO> update(UserDTO usertDTO) {
        UserEntity userEntity = UserMapperEntity.toEntity(usertDTO);
        return userMongoRepository.save(userEntity).map(UserMapperEntity::fromEntity);
    }

    @Override
    public Mono<UserDTO> findByEmail(String email) {
        return userMongoRepository.findByEmail(email).map(UserMapperEntity::fromEntity);
    }


    @Override
    public Mono<UserDTO> findById(String id) {
        return userMongoRepository.findById(id).map(UserMapperEntity::fromEntity);
    }
}
