package ec.com.airsofka.seat.values.objects;

import ec.com.airsofka.generics.interfaces.IValueObject;

public class Column implements IValueObject<String> {
    private final String value;

    public Column(String value) {
        this.value = validate(value);
    }

    public static Column of(final String value) {
        return new Column(value);
    }

    @Override
    public String getValue() {
        return this.value;
    }

    private String validate(final String value) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException("The column cannot be empty");
        }

        return value;
    }
}
