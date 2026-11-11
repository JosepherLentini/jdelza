package com.jdelza.utils.events;

import com.jdelza.utils.enums.Directions;
import com.jdelza.utils.interfaces.Environment;

public class ChangeZone {
    private Directions direction;


    public ChangeZone(Directions direction) {
        this.direction = direction;
    }

    public Directions getDirection() {
        return direction;
    }


}
