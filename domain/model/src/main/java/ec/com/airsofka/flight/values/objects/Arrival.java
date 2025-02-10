package ec.com.airsofka.flight.values.objects;

import ec.com.airsofka.generics.interfaces.IValueObject;

import java.time.LocalDateTime;

public class Arrival implements IValueObject<LocalDateTime> {
    private final LocalDateTime value;

    public Arrival(LocalDateTime value) {
        this.value = validate(value);
    }

    public static Arrival of(final LocalDateTime value) {
        return new Arrival(value);
    }

    @Override
    public LocalDateTime getValue() {
        return this.value;
    }

    private LocalDateTime validate(final LocalDateTime value) {
        if (value == null) {
            throw new IllegalArgumentException("The arrival cannot be null");
        }

        return value;
    }
}
