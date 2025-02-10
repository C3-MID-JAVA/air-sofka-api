package ec.com.airsofka.plane.queries.responses;

import ec.com.airsofka.plane.PlaneStatus;

public class PlaneResponse {
    private final String id;
    private final PlaneStatus state;
    private final String model;

    public PlaneResponse(String id, PlaneStatus state, String model) {
        this.id = id;
        this.state = state;
        this.model = model;
    }

    public String getId() {
        return id;
    }

    public PlaneStatus getState() {
        return state;
    }

    public String getModel() {
        return model;
    }
}
