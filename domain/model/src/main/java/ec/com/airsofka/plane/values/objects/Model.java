package ec.com.airsofka.plane.values.objects;

import ec.com.airsofka.generics.interfaces.IValueObject;

public class Model implements IValueObject<String> {
    private final String value;

    public Model(String value) {
        this.value = validate(value);
    }

    public static Model of(final String value) {
        return new Model(value);
    }

    @Override
    public String getValue() {
        return this.value;
    }

    private String validate(final String value) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException("The model cannot be empty");
        }

        return value;
    }
}
