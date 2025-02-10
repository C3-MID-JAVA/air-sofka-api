package ec.com.airsofka.user.values.objects;

import ec.com.airsofka.generics.interfaces.IValueObject;

public class FirstLastName implements IValueObject<String> {

    private final String value;
    public FirstLastName(final String value) {
        this.value = validate(value);
    }

    public static FirstLastName of(final String value) {
        return new FirstLastName(value);
    }

    @Override
    public String getValue() {
        return this.value;
    }

    private String validate(final String value) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException("The first last name cannot be empty");
        }

        return value;
    }
}
