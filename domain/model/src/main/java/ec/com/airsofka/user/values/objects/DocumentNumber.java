package ec.com.airsofka.user.values.objects;

import ec.com.airsofka.generics.interfaces.IValueObject;

public class DocumentNumber implements IValueObject<String> {

    private final String value;

    public DocumentNumber(String value) {
        this.value = validate(value);
    }

    public static DocumentNumber of(String value) {
        return new DocumentNumber(value);
    }

    @Override
    public String getValue() {
        return this.value;
    }

    private String validate(final String value) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException("The document number cannot be empty");
        }

        return value;
    }
}
