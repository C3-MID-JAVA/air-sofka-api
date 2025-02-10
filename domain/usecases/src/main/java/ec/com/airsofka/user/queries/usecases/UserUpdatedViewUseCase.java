package ec.com.airsofka.user.queries.usecases;

import ec.com.airsofka.gateway.IUserRepository;
import ec.com.airsofka.gateway.dto.UserDTO;
import ec.com.airsofka.generics.interfaces.IUseCaseAccept;

public class UserUpdatedViewUseCase implements IUseCaseAccept<UserDTO, Void> {
   private final IUserRepository userRepository;
   public UserUpdatedViewUseCase(final IUserRepository userRepository) {
       this.userRepository = userRepository;
   }

    @Override
    public void accept(UserDTO userDTO) {
        this.userRepository.update(userDTO).subscribe();
    }
}
