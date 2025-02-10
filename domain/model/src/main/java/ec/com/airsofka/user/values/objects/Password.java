package ec.com.airsofka.user.values.objects;

import ec.com.airsofka.generics.interfaces.IValueObject;

public class Password implements IValueObject<String> {

    private final String value;

    public Password(String value) {
        this.value = validate(value);
    }

    public static Password of(String value) {
        return new Password(value);
    }

    @Override
    public String getValue() {
        return this.value;
    }

    private String validate(final String value) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException("The password cannot be empty");
        }

        return value;
    }
}
