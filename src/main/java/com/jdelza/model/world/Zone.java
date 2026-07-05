package com.jdelza.model.world;

import com.jdelza.model.characters.Player;

public class Zone {

    public Tile[][] zone;
    public Player player;

    /**
     * Contructor
     * @param y     zone height
     * @param x     zone width
     * @param player    player of the game
     */
    public Zone(int y, int x, Player player) {
        this.zone = new Tile[y][x];
        this.player = player;
    }

    //Get methods
    public Tile[][] getZone() {
        return zone;
    }

    /**
     * Methods to add or set a Tile objent placed in y, coordinates in to the zone
     * @param tile
     * @param y
     * @param x
     */
    public void addTile(Tile tile, int y, int x){
        zone[y][x] = tile;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int h = 0; h < zone.length; h++){
            for (int w = 0; w < zone[0].length; w++){
                if (player.getPosition().getX() == w && player.getPosition().getY() == h){
                    sb.append(player.toString());
                } else {sb.append(zone[h][w].toString()+" ");}

            }
            sb.append("\n");
        }
        return sb.toString();
    }


}
