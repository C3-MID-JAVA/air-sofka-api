package ec.com.airsofka.user.values.objects;

import ec.com.airsofka.generics.interfaces.IValueObject;

public class Prefix implements IValueObject<String> {

    private final String value;

    public Prefix(String value) {
        this.value = validate(value);
    }


    public static Prefix of(String value) {
        return new Prefix(value);
    }

    @Override
    public String getValue() {
        return this.value;
    }

    private String validate(final String value) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException("The prefix cannot be empty");
        }

        return value;
    }
}
