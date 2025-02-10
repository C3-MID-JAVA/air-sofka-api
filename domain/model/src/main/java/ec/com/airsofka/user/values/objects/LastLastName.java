package ec.com.airsofka.user.values.objects;

import ec.com.airsofka.generics.interfaces.IValueObject;

public class LastLastName implements IValueObject<String> {

    private final String value;

    public LastLastName(String value) {
        this.value = validate(value);
    }

    public static LastLastName of(String value) {
        return new LastLastName(value);
    }

    @Override
    public String getValue() {
        return this.value;
    }

    private String validate(final String value) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException("The last lastname cannot be empty");
        }

        return value;
    }
}
