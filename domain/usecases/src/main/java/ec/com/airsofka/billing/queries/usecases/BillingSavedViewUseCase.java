package ec.com.airsofka.billing.queries.usecases;

import ec.com.airsofka.gateway.IBillingRepository;
import ec.com.airsofka.gateway.dto.BillingDTO;
import ec.com.airsofka.generics.interfaces.IUseCaseAccept;

public class BillingSavedViewUseCase implements IUseCaseAccept<BillingDTO, Void> {

    private  final IBillingRepository billingRepository;


    public BillingSavedViewUseCase(IBillingRepository billingRepository) {
        this.billingRepository = billingRepository;
    }


    @Override
    public void accept(BillingDTO request) {
        billingRepository.save(request).subscribe();
    }
}
