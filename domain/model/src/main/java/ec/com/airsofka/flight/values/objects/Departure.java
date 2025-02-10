package ec.com.airsofka.flight.values.objects;

import ec.com.airsofka.generics.interfaces.IValueObject;

import java.time.LocalDateTime;

public class Departure implements IValueObject<LocalDateTime> {
    private final LocalDateTime value;

    public Departure(LocalDateTime value) {
        this.value = validate(value);
    }

    public static Departure of(final LocalDateTime value) {
        return new Departure(value);
    }

    @Override
    public LocalDateTime getValue() {
        return this.value;
    }

    private LocalDateTime validate(final LocalDateTime value) {
        if (value == null) {
            throw new IllegalArgumentException("The departure cannot be empty");
        }

        return value;
    }
}
