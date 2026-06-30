package com.jdelza.model.entities;

public abstract class Entity {
    private Coordinates position;

    public Entity(Coordinates position) {
        this.position = position;
    }

    public Entity(int x, int y) {
        this.position = new Coordinates(x,y);
    }

    //Get methods
    public Coordinates getPosition() {return position;}

    //Set methods
    public void setPosition(Coordinates position) {this.position = position;}
}
