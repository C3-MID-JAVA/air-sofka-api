package ec.com.airsofka.user.values.objects;

import ec.com.airsofka.generics.interfaces.IValueObject;

public class NumberOfFlights implements IValueObject<Integer> {

    private final Integer value;

    public NumberOfFlights(Integer value) {
        this.value = validate(value);
    }

    public static NumberOfFlights of(Integer value) {
        return new NumberOfFlights(value);
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    private Integer validate(final Integer value) {
        if (value == null) {
            throw new IllegalArgumentException("The number of flights cannot be null");
        }

        return value;
    }

}
