package ec.com.airsofka.user.values.objects;

import ec.com.airsofka.generics.interfaces.IValueObject;

public class Frequent implements IValueObject<Boolean> {

    private final Boolean value;

    public Frequent(Boolean value) {
        this.value = validate(value);
    }

    public static Frequent of (final Boolean value) {
        return new Frequent(value);
    }

    @Override
    public Boolean getValue() {
        return this.value;
    }

    private Boolean validate(final Boolean value) {
        if (value == null) {
            throw new IllegalArgumentException("The frequent value must be true and cannot be null");
        }
        return value;
    }
}
