package com.jdelza.model.world;

import com.jdelza.model.characters.Enemy;
import com.jdelza.model.characters.Player;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.weapons.Weapon;
import com.jdelza.utils.enums.Dimensions;
import com.jdelza.utils.enums.Directions;
import com.jdelza.utils.interfaces.Environment;

import java.util.ArrayList;
import java.util.List;

public class Zone {

    private Tile[][] zone;

    //Enemies
    private List<Enemy> enemies;                 //Some zone contains enemies

    //Weapons
    private List<Weapon> weapons;

    //Zone position
    private Coordinates zoneMapPosition;        //Zone map coordinates

    //Environments
    //private List<Environment> environments;


    /**
     * Zone constructor
     */
    public Zone(Coordinates zoneMapPosition) {

        //Standard and global zone dimensions
        int zoneRows = Dimensions.ZONE_ROWS.get();
        int zoneColumns = Dimensions.ZONE_COLUMNS.get();

        //Class fields inizialization
        this.zone = new Tile[zoneRows][zoneColumns];

        this.zoneMapPosition = zoneMapPosition;
        this.enemies = new ArrayList<>();
        this.weapons = new ArrayList<>();
        //this.environments = new ArrayList<>();

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
    public Tile getTile(Coordinates coordinates){
        return this.getZone()[coordinates.getY()][coordinates.getX()];
    }
    public List<Weapon> getWeapons() {return weapons;}
    //public List<Environment> getEnvironments() {return environments;}

    public Coordinates getZoneMapPosition() {
        return zoneMapPosition;
    }

    //set methods
    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    /**
     * This method allows to add an enemy
     * @param e     new enemy
     */
    public void addEnemy(Enemy e){this.enemies.add(e);}

    /**
     * This function allows to add new environment to the list of environments
     * @param environment new environment to insert
     */
    //public void addEnvironment(Environment environment){environments.add(environment);}

    /**
     * Methods to add or set a Tile objent placed in y, coordinates in to the zone
     * @param tile
     * @param y
     * @param x
     */
    public void addTile(Tile tile, int y, int x){
        zone[y][x] = tile;
    }

    /**
     * This method allow to add new weapon in the zone
     * @param newWeapon
     */
    public void addWeapon(Weapon newWeapon){
        this.weapons.add(newWeapon);
    }

    /**
     * This method allow to remove a weapon
     * @param weapon
     */
    public void removeWeapon(Weapon weapon){
        this.weapons.remove(weapon);
    }


    @Override
    public String toString(){

        return "Zone: "+ this.zoneMapPosition.getX() + "  " + this.zoneMapPosition.getY();

    }






}
