package com.jdelza.model.world;

import com.jdelza.model.entities.Coordinates;

public abstract class GameMap {

    protected Zone[][] map;

    private boolean zoneChanging = false;


    public GameMap() {

    }

    //Get methods
    public Zone[][] getMap() {
        return map;
    }

    public Zone getZone(Coordinates coordinates){return map[coordinates.getY()][coordinates.getX()];}

    public Zone getZone(int x, int y){

        if(x < map[0].length && y < map.length){
            return map[y][x];
        }

        return null;
    }

    public boolean isZoneChanging() {
        return zoneChanging;
    }

    public void setZoneChanging(boolean zoneChanging) {
        this.zoneChanging = zoneChanging;
    }

    //Abstract methods
    public abstract void setLogicMap();
}
