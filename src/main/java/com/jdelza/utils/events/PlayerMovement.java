package com.jdelza.utils.events;

import com.jdelza.model.entities.Coordinates;
import com.jdelza.utils.enums.Action;
import com.jdelza.utils.enums.Directions;

public class PlayerMovement {


    private Directions direction;


    public PlayerMovement(Directions direction) {

        this.direction = direction;
    }


    //Get methods
    public Directions getDirection() {
        return direction;
    }


}
