package com.jdelza.model.world;

import com.jdelza.model.characters.Player;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.utils.Dimensions;

public class Zone {

    public Tile[][] zone;
    public Player player;

    //Zone position
    private Coordinates zoneMapPosition;        //Zone map coordinates

    /**
     * Contructor
     * @param player    player of the game
     */
    public Zone(Player player, Coordinates zoneMapPosition) {

        //Standard and global zone dimensions
        int zoneRows = Dimensions.ZONE_ROWS.getInt();
        int zoneColumns = Dimensions.ZONE_COLUMNS.getInt();


        this.zone = new Tile[zoneRows][zoneColumns];
        this.player = player;
        this.zoneMapPosition = zoneMapPosition;

        for (int i = 0; i<zoneRows; i++){
            for (int j=0; j<zoneColumns; j++){
                zone[i][j] = new Tile(new Coordinates(j,i));
            }
        }

    }

    //Get methods
    public Tile[][] getZone() {
        return zone;
    }

    public Player getPlayer() {
        return player;
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
                if (player != null & player.getPosition().getX() == w && player.getPosition().getY() == h){ // &
                    sb.append(player.toString());

                } else {sb.append(zone[h][w].toString()+" ");}

            }
            sb.append("\n");

        }
        sb.append("Zone: "+ this.zoneMapPosition.getX() + "  " + this.zoneMapPosition.getY());
        return sb.toString();
    }


}
