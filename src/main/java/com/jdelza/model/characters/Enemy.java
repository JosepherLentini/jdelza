package com.jdelza.model.characters;

import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.entities.Entity;

/**
 * This class describe an enemy which attacks the player
 */
public abstract class Enemy {

    /**
     * This method allow to define the how to attack the player
     */
    public abstract void attack();

}
