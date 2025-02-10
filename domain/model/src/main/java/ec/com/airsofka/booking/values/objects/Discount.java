package ec.com.airsofka.booking.values.objects;

import ec.com.airsofka.generics.interfaces.IValueObject;

import java.math.BigDecimal;

public class Discount implements IValueObject<BigDecimal> {
    private final BigDecimal value;

    private Discount(final BigDecimal value){
        this.value = validate(value);
    }

    public static Discount  of(final BigDecimal value){
        return new Discount(value);
    }


    @Override
    public BigDecimal getValue() {
        return value;
    }

    private BigDecimal validate(final BigDecimal value){
        if(value == null){
            throw new IllegalArgumentException("The discount can't be null");
        }

        return value;
    }
}
