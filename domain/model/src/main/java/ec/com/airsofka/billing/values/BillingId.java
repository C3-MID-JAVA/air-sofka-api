package ec.com.airsofka.billing.values;


import ec.com.airsofka.generics.utils.Identity;

public class BillingId extends Identity {


    public BillingId() {
        super();
    }

    private BillingId(final String id) {
        super(id);
    }

    public static BillingId of(final String id) {
        return new BillingId(id);
    }
}
