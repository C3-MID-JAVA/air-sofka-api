package ec.com.airsofka.maintenance.values.objects;

import ec.com.airsofka.generics.interfaces.IValueObject;

import java.time.LocalDateTime;

public class End implements IValueObject<LocalDateTime> {
    private final LocalDateTime value;

    public End(LocalDateTime value) {
        this.value = validate(value);
    }

    public static End of(final LocalDateTime value) {
        return new End(value);
    }

    @Override
    public LocalDateTime getValue() {
        return this.value;
    }

    private LocalDateTime validate(final LocalDateTime value) {
        if (value == null) {
            throw new IllegalArgumentException("The end cannot be empty");
        }

        return value;
    }
}
