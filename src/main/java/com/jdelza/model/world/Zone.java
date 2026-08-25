package com.jdelza.model.world;

import com.jdelza.model.characters.Enemy;
import com.jdelza.model.characters.Player;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.utils.enums.Dimensions;

import java.util.ArrayList;
import java.util.List;

public class Zone {

    private Tile[][] zone;
    private Player player;

    //Enemies
    private List<Enemy> enemies;                 //Some zone contains enemies

    //Zone position
    private Coordinates zoneMapPosition;        //Zone map coordinates

    /**
     * Zone constructor
     * @param player    player of the game
     */
    public Zone(Player player, Coordinates zoneMapPosition) {

        //Standard and global zone dimensions
        int zoneRows = Dimensions.ZONE_ROWS.get();
        int zoneColumns = Dimensions.ZONE_COLUMNS.get();

        //Class fields inizialization
        this.zone = new Tile[zoneRows][zoneColumns];
        this.player = player;
        this.zoneMapPosition = zoneMapPosition;
        this.enemies = new ArrayList<>();

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
    public List<Enemy> getEnemies() {return enemies;}
    public Player getPlayer() {
        return player;
    }

    /**
     * This method allows to add an enemy
     * @param e     new enemy
     */
    public void addEnemy(Enemy e){this.enemies.add(e);}

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
                if (player != null && player.getPosition().getX() == w && player.getPosition().getY() == h){ // &
                    sb.append(player.toString());

                } else {sb.append(zone[h][w].isWalkable() ? "0 " : "1 ");}

            }
            sb.append("\n");

        }
        sb.append("Zone: "+ this.zoneMapPosition.getX() + "  " + this.zoneMapPosition.getY());
        return sb.toString();
    }


}
