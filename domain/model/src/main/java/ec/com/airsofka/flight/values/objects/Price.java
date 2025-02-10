package ec.com.airsofka.flight.values.objects;

import ec.com.airsofka.generics.interfaces.IValueObject;

public class Price implements IValueObject<Double> {
    private final Double value;

    public Price(Double value) {
        this.value = validate(value);
    }

    public static Price of(final Double value) {
        return new Price(value);
    }

    @Override
    public Double getValue() {
        return this.value;
    }

    private Double validate(final Double value) {
        if (value == null) {
            throw new IllegalArgumentException("The price cannot be null");
        }

        return value;
    }
}
