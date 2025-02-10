package ec.com.airsofka.maintenance.commands;

import ec.com.airsofka.generics.utils.Command;

import java.time.LocalDateTime;

public class CreateMaintenanceCommand extends Command {
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final String idPlane;

    public CreateMaintenanceCommand(LocalDateTime start, LocalDateTime end, String idPlane) {
        super(null);
        this.start = start;
        this.end = end;
        this.idPlane = idPlane;
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
