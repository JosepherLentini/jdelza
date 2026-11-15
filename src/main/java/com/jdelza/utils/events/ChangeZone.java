package com.jdelza.utils.events;

import com.jdelza.utils.enums.Directions;
import com.jdelza.utils.enums.StartEndStatus;
import com.jdelza.utils.interfaces.Environment;

public class ChangeZone {
    private Directions direction;
    private StartEndStatus status;

    public ChangeZone(Directions direction, StartEndStatus status) {


        this.direction = direction;
        this.status = status;
    }

    public Directions getDirection() {
        return direction;
    }

    public StartEndStatus getStatus() {
        return status;
    }
}
