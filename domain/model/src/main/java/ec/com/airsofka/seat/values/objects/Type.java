package ec.com.airsofka.seat.values.objects;

import ec.com.airsofka.generics.interfaces.IValueObject;
import ec.com.airsofka.seat.SeatClass;

public class Type implements IValueObject<SeatClass> {
    private final SeatClass value;

    public Type(SeatClass value) {
        this.value = validate(value);
    }

    public static Type of(final SeatClass value) {
        return new Type(value);
    }

    @Override
    public SeatClass getValue() {
        return this.value;
    }

    private SeatClass validate(final SeatClass value) {
        if (value == null) {
            throw new IllegalArgumentException("The type cannot be null");
        }

        return value;
    }
}
