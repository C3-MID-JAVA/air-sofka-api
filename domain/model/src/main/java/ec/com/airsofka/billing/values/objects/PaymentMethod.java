package ec.com.airsofka.billing.values.objects;


import ec.com.airsofka.generics.interfaces.IValueObject;

public class PaymentMethod implements IValueObject<String> {
    private final String value;

    private PaymentMethod(final String value){
        this.value = validate(value);
    }

    public static PaymentMethod of(final String value){
        return new PaymentMethod(value);
    }


    @Override
    public String getValue() {
        return value;
    }

    private String validate(final String value){
        if(value == null){
            throw new IllegalArgumentException("The payment method can't be null");
        }

        return value;
    }
}

