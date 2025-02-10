package ec.com.airsofka.booking.values.objects;

import ec.com.airsofka.generics.interfaces.IValueObject;

import java.math.BigDecimal;

public class TotalPrice  implements IValueObject<BigDecimal> {
    private final BigDecimal value;

    private TotalPrice(final BigDecimal value){
        this.value = validate(value);
    }

    public static TotalPrice  of(final BigDecimal value){
        return new TotalPrice(value);
    }


    @Override
    public BigDecimal getValue() {
        return value;
    }

    private BigDecimal validate(final BigDecimal value){
        if(value == null){
            throw new IllegalArgumentException("The total price can't be null");
        }

        return value;
    }
}