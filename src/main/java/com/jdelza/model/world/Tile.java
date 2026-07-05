package com.jdelza.model.world;

import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.entities.Entity;

public class Tile extends Entity {

    public String path;
    public boolean walkable;

    public Tile(Coordinates position, String path) {
        super(position);
        this.path = path;
        this.walkable = true;
    }

    //Get methods
    public String getPath() {return path;}
    public boolean isWalkable() {return walkable;}

    //Set methods
    public String setPath(String path){return this.path;}
    public void setWalkable(boolean walkable) {this.walkable = walkable;}

    @Override
    public String toString(){
        return walkable ? "." : "X";
    }
}
