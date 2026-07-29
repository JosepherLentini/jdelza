package com.jdelza.utils.enums.characters;

import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.entities.Entity;

/**
 * This class describes a character capable of moving
 */

public abstract class GameCharacter extends Entity{


    /**
     *
     * @param position
     */
    public GameCharacter(Coordinates position) {
        super(position);
    }



}
