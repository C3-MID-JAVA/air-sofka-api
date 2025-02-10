package ec.com.airsofka.seat.values.objects;

import ec.com.airsofka.generics.interfaces.IValueObject;
import ec.com.airsofka.seat.SeatStatus;

public class Status implements IValueObject<SeatStatus> {
    private final SeatStatus value;

    public Status(SeatStatus value) {
        this.value = validate(value);
    }

    public static Status of(final SeatStatus value) {
        return new Status(value);
    }

    @Override
    public SeatStatus getValue() {
        return this.value;
    }

    private SeatStatus validate(final SeatStatus value) {
        if (value == null) {
            throw new IllegalArgumentException("The status cannot be null");
        }

        return value;
    }
}
