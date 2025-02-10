package ec.com.airsofka.user.values.objects;

import ec.com.airsofka.generics.interfaces.IValueObject;

public class Phone implements IValueObject<String> {

    private final String value;

    public Phone(String value) {
        this.value = validate(value);
    }

    public static Phone of(String value) {
        return new Phone(value);
    }


    @Override
    public String getValue() {
        return this.value;
    }

    private String validate(final String value) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException("The phone cannot be empty");
        }

        return value;
    }
}
