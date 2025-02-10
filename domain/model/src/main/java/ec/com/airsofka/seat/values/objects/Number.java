package ec.com.airsofka.seat.values.objects;

import ec.com.airsofka.generics.interfaces.IValueObject;

public class Number implements IValueObject<String> {
    private final String value;

    public Number(String value) {
        this.value = validate(value);
    }

    public static Number of(final String value) {
        return new Number(value);
    }

    @Override
    public String getValue() {
        return this.value;
    }

    private String validate(final String value) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException("The number cannot be empty");
        }

        return value;
    }
}
