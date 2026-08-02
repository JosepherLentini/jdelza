package com.jdelza.utils.events;

import com.jdelza.model.entities.Coordinates;
import com.jdelza.utils.enums.Directions;

public class PlayerMovement {

    private boolean zoneChanged;
    private Directions direction;


    public PlayerMovement(boolean zoneChanged, Directions direction) {
        this.zoneChanged = zoneChanged;
        this.direction = direction;
    }


    //Get methods
    public Directions getDirection() {
        return direction;
    }

    public boolean isZoneChanged() {
        return zoneChanged;
    }
}
