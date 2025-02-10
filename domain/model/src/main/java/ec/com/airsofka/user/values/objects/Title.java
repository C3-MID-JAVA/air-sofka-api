package ec.com.airsofka.user.values.objects;

import ec.com.airsofka.generics.interfaces.IValueObject;

public class Title implements IValueObject<String> {

    private final String value;


    public Title(String value) {
        this.value = validate(value);
    }

    public static Title of(String value) {
        return new Title(value);
    }

    @Override
    public String getValue() {
        return this.value;
    }

    private String validate(final String value) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException("The title cannot be empty");
        }

        return value;
    }
}
