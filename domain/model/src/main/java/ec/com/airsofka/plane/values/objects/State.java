package ec.com.airsofka.plane.values.objects;

import ec.com.airsofka.generics.interfaces.IValueObject;
import ec.com.airsofka.plane.PlaneStatus;

public class State implements IValueObject<PlaneStatus> {
    private final PlaneStatus value;

    public State(PlaneStatus value) {
        this.value = validate(value);
    }

    public static State of(final PlaneStatus value) {
        return new State(value);
    }

    @Override
    public PlaneStatus getValue() {
        return this.value;
    }

    private PlaneStatus validate(final PlaneStatus value) {
        if (value == null) {
            throw new IllegalArgumentException("The state cannot be null");
        }

        return value;
    }
}
