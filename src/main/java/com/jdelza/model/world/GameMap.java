package com.jdelza.model.world;

import com.jdelza.model.entities.Coordinates;

public abstract class GameMap {

    protected Zone[][] map;

    public GameMap() {

    }

    //Get methods
    public Zone[][] getMap() {
        return map;
    }

    public Zone getZone(Coordinates coordinates){return map[coordinates.getY()][coordinates.getX()];}

    //Abstract methods
    public abstract void setLogicMap();
}
