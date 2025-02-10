package ec.com.airsofka.plane.commands;

import ec.com.airsofka.generics.utils.Command;
import ec.com.airsofka.plane.PlaneStatus;

public class CreatePlaneCommand extends Command {
    private final String model;

    public CreatePlaneCommand(String model) {
        super(null);
        this.model = model;
    }

    public String getModel() {
        return model;
    }
}
