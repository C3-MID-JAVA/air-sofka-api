package ec.com.airsofka.maintenance.queries.responses;

import java.time.LocalDateTime;

public class MaintenanceResponse {
    private final String id;
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final String idPlane;

    public MaintenanceResponse(String id, LocalDateTime start, LocalDateTime end, String idPlane) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.idPlane = idPlane;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public String getIdPlane() {
        return idPlane;
    }
}
