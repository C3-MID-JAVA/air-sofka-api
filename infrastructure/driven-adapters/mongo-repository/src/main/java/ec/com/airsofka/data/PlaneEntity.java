package ec.com.airsofka.data;

import ec.com.airsofka.plane.PlaneStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "planes")
public class PlaneEntity {
    @Id
    private String id;

    private PlaneStatus state;

    private String model;

    public PlaneEntity() {
    }

    public PlaneEntity(String id, PlaneStatus state, String model) {
        this.id = id;
        this.state = state;
        this.model = model;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PlaneStatus getState() {
        return state;
    }

    public void setState(PlaneStatus state) {
        this.state = state;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
