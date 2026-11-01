package com.jdelza.utils.events;

import com.jdelza.utils.enums.Directions;

public class WalkMovement {
    private Directions direction;

    public WalkMovement(Directions direction) {
        this.direction = direction;
    }

    public Directions getDirection() {
        return direction;
    }
}
