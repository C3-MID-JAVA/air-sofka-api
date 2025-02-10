package ec.com.airsofka.contact.queries.usecases;

import ec.com.airsofka.gateway.IContactRepository;
import ec.com.airsofka.gateway.dto.ContactDTO;
import ec.com.airsofka.generics.interfaces.IUseCaseAccept;

public class ContactSavedViewUseCase implements IUseCaseAccept<ContactDTO, Void> {

    private  final IContactRepository contactRepository;

    public ContactSavedViewUseCase(IContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public void accept(ContactDTO request) {
        contactRepository.save(request).subscribe();
    }
}
