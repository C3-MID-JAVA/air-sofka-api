package ec.com.airsofka.user.values.objects;

import ec.com.airsofka.generics.interfaces.IValueObject;

public class Name implements IValueObject<String> {

    private final String value;

    public Name(final String value) {
        this.value = validate(value);
    }

    public static Name of(final String value) {
        return new Name(value);
    }

    @Override
    public String getValue() {
        return this.value;
    }
    private String validate(final String value) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException("The name cannot be empty");
        }

        return value;
    }

}
