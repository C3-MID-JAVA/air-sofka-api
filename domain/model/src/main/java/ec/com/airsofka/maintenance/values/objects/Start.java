package ec.com.airsofka.maintenance.values.objects;

import ec.com.airsofka.generics.interfaces.IValueObject;

import java.time.LocalDateTime;

public class Start implements IValueObject<LocalDateTime> {
    private final LocalDateTime value;

    public Start(LocalDateTime value) {
        this.value = validate(value);
    }

    public static Start of(final LocalDateTime value) {
        return new Start(value);
    }

    @Override
    public LocalDateTime getValue() {
        return this.value;
    }

    private LocalDateTime validate(final LocalDateTime value) {
        if (value == null) {
            throw new IllegalArgumentException("The start cannot be empty");
        }

        return value;
    }
}
