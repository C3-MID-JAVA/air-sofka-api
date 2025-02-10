package ec.com.airsofka.maintenance.values;

import ec.com.airsofka.generics.utils.Identity;

public class MaintenanceId extends Identity {
    public MaintenanceId() {
        super();
    }

    private MaintenanceId(final String id) {
        super(id);
    }

    public static MaintenanceId of(final String id) {
        return new MaintenanceId(id);
    }

}
