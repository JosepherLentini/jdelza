package com.jdelza.model.characters;

import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.entities.Entity;
import com.jdelza.model.interfaces.Movable;

/**
 * This class describes a character capable of moving
 */

public abstract class Character extends Entity implements Movable {

    public Character(Coordinates position) {
        super(position);
    }


}
