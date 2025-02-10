package ec.com.airsofka.seat.values.objects;

import ec.com.airsofka.generics.interfaces.IValueObject;

public class Row implements IValueObject<Integer> {
    private final Integer value;

    public Row(Integer value) {
        this.value = validate(value);
    }

    public static Row of(final Integer value) {
        return new Row(value);
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    private Integer validate(final Integer value) {
        if (value == null) {
            throw new IllegalArgumentException("The row cannot be null");
        }

        return value;
    }
}
