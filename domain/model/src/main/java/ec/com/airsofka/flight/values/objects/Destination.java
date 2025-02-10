package ec.com.airsofka.flight.values.objects;

import ec.com.airsofka.generics.interfaces.IValueObject;

public class Destination implements IValueObject<String> {
    private final String value;

    public Destination(String value) {
        this.value = validate(value);
    }

    public static Destination of(final String value) {
        return new Destination(value);
    }

    @Override
    public String getValue() {
        return this.value;
    }

    private String validate(final String value) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException("The destination cannot be empty");
        }

        return value;
    }
}
