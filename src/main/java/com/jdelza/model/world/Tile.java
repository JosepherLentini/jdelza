package com.jdelza.model.world;

import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.entities.Entity;

public class Tile extends Entity {

    public int tileId;
    public boolean walkable;

    public Tile(Coordinates position) {
        super(position);
        this.tileId = tileId;
        this.walkable = true;
    }

    //Get methods
    public int getPath() {return tileId;}
    public boolean isWalkable() {return walkable;}

    //Set methods
    public void setTileId(int tileId) {this.tileId = tileId;}
    public void setWalkable(boolean walkable) {this.walkable = walkable;}

    @Override
    public String toString(){
        return walkable ? "." : "X";
    }
}
