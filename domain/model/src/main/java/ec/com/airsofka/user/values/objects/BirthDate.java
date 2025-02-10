package ec.com.airsofka.user.values.objects;

import ec.com.airsofka.generics.interfaces.IValueObject;

import java.time.LocalDateTime;

public class BirthDate implements IValueObject <LocalDateTime> {

    private final LocalDateTime value;

    public BirthDate(LocalDateTime value) {
        this.value = validate(value);
    }

    public static BirthDate of (LocalDateTime value) {
        return new BirthDate(value);
    }


    @Override
    public LocalDateTime getValue() {
        return this.value;
    }

    private LocalDateTime validate(final LocalDateTime value) {
        if (value == null) {
            throw new IllegalArgumentException("The birth date cannot be null");
        }

        return value;
    }

}
