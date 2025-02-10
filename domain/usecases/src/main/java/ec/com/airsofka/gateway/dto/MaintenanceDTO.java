package ec.com.airsofka.gateway.dto;

import java.time.LocalDateTime;

public class MaintenanceDTO {
    private String id;
    private LocalDateTime start;
    private LocalDateTime end;
    private String idPlane;

    public MaintenanceDTO(String id, LocalDateTime start, LocalDateTime end, String idPlane) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.idPlane = idPlane;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public String getIdPlane() {
        return idPlane;
    }

    public void setIdPlane(String idPlane) {
        this.idPlane = idPlane;
    }
}
