package ec.com.airsofka.flight.values.objects;

import ec.com.airsofka.generics.interfaces.IValueObject;

public class Origin implements IValueObject<String> {
    private final String value;

    public Origin(String value) {
        this.value = validate(value);
    }

    public static Origin of(final String value) {
        return new Origin(value);
    }

    @Override
    public String getValue() {
        return this.value;
    }

    private String validate(final String value) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException("The origin cannot be empty");
        }

        return value;
    }
}
