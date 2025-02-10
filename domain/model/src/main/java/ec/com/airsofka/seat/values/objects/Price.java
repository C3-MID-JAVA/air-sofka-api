package ec.com.airsofka.seat.values.objects;

import ec.com.airsofka.generics.interfaces.IValueObject;

import java.math.BigDecimal;

public class Price implements IValueObject<BigDecimal> {
    private final BigDecimal value;

    public Price(BigDecimal value) {
        this.value = validate(value);
    }

    public static Price of(final BigDecimal value) {
        return new Price(value);
    }

    @Override
    public BigDecimal getValue() {
        return this.value;
    }

    private BigDecimal validate(final BigDecimal value) {
        if (value == null) {
            throw new IllegalArgumentException("The price cannot be null");
        }

        return value;
    }
}
