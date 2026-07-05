package com.jdelza.model.world;

import com.jdelza.model.characters.Player;
import com.jdelza.model.entities.Coordinates;

/**
 * This class represent the current logic view of the game
 */
public class Map {
    public Zone[][] map;
    public Player player;
    /**
     * Contructor
     * @param heigt     height of the map
     * @param width     width of the map
     */
    public Map(int heigt, int width) {

        this.map = new Zone[heigt][width];

    }

    //Get methods
    public Zone[][] getMap() {
        return map;
    }

    //Set methods
    public void setMap(Zone[][] map) {
        this.map = map;
    }

    /**
     * Add zone in to the map by coordinates expressed by an object of type "Coordinates"
     * @param zone
     * @param coordinates
     */
    public void addZone(Zone zone, Coordinates coordinates){
        int y = coordinates.getY();
        int x = coordinates.getX();
        map[y][x] = zone;
    }

    /**
     * Add zone in to the map by coordinates int y and int x
     * @param zone      a zone type object to be inserted
     * @param y         height position
     * @param x         width position
     */
    public void addZone(Zone zone, int y, int x){
        map[y][x] = zone;
    }


    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        for (int h = 0; h < map.length; h++){

            for (int w = 0; w < map[0].length; w++){
                sb.append(map[h][w].toString());
            }
            sb.append("\n");

        }

        return sb.toString();

    }



}
