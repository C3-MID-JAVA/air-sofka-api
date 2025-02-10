package ec.com.airsofka.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "maintenances")
public class MaintenanceEntity {

    @Id
    private String id;

    private LocalDateTime start;

    private LocalDateTime end;

    private String idPlane;

    public MaintenanceEntity() {
    }

    public MaintenanceEntity(String id, LocalDateTime start, LocalDateTime end, String idPlane) {
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
