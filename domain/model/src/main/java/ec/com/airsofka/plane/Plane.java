package ec.com.airsofka.plane;

import ec.com.airsofka.generics.utils.Entity;
import ec.com.airsofka.plane.values.PlaneId;
import ec.com.airsofka.plane.values.objects.Model;
import ec.com.airsofka.plane.values.objects.State;

public class Plane extends Entity<PlaneId> {
    private final State state;
    private final Model model;

    public Plane(PlaneId id, State state, Model model) {
        super(id);
        this.state = state;
        this.model = model;
    }

    public State getState() {
        return state;
    }

    public Model getModel() {
        return model;
    }
}
