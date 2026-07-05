package com.jdelza.model.characters;

import com.jdelza.model.Directions;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.entities.Entity;

/**
 * This class define the player of the game
 */
public class Player extends Character {

    public Player(Coordinates position) {
        super(position);
    }

    @Override
    public void move(Directions direction) {
        this.setPosition(new Coordinates(
                this.getPosition().getX() + direction.getX(),
                this.getPosition().getY() + direction.getY()
        ));
    }

    @Override
    public String toString(){
        return "P ";
    }

}
