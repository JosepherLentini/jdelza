package com.jdelza.model.entities;

/**
 * This class defines everything that can appear on the screen
 */
public abstract class Entity {
    private Coordinates position;

    public Entity(Coordinates position) {
        this.position = position;
    }

    //Get methods
    public Coordinates getPosition() {return position;}

    //Set methods
    public void setPosition(Coordinates position) {this.position = position;}
}
