package ec.com.airsofka.plane.values;

import ec.com.airsofka.generics.utils.Identity;

public class PlaneId extends Identity {
    public PlaneId() {
        super();
    }

    private PlaneId(final String id) {
        super(id);
    }

    public static PlaneId of(final String id) {
        return new PlaneId(id);
    }
}
