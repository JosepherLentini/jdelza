package com.jdelza.utils.events;

import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.entities.Position;
import com.jdelza.utils.enums.Directions;
import com.jdelza.utils.enums.StartEndStatus;
import com.jdelza.utils.interfaces.Environment;

public class ChangeZone {
    private Directions direction;
    private StartEndStatus status;
    private Coordinates zonePosition;

    public ChangeZone(Coordinates zonePosition, Directions direction, StartEndStatus status) {


        this.direction = direction;
        this.status = status;
        this.zonePosition = zonePosition;
    }

    public Directions getDirection() {
        return direction;
    }
    public StartEndStatus getStatus() {return status;}
    public Coordinates getZonePosition() {return zonePosition;}
}
