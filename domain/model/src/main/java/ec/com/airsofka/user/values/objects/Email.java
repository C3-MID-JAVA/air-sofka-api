package ec.com.airsofka.user.values.objects;

import ec.com.airsofka.generics.interfaces.IValueObject;

public class Email implements IValueObject<String> {

    private final String value;

    public Email(String value) {
        this.value = validate(value);
    }

    public static Email of(String value) {
        return new Email(value);
    }

    public String getValue() {
        return value;
    }

    private String validate(final String value) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException("The email cannot be empty");
        }

        return value;
    }
}
