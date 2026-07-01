package com.jdelza.model.entities;

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
