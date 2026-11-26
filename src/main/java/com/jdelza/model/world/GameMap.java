package com.jdelza.model.world;

import com.jdelza.model.characters.Player;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.entities.Position;
import com.jdelza.model.items.Rupie;
import com.jdelza.utils.enums.*;
import com.jdelza.utils.interfaces.Environment;

public abstract class GameMap {

    protected Zone[][] map;
    private boolean zoneChanging = false;


    public GameMap() {

    }

    //Get methods
    public Zone[][] getMap() {
        return map;
    }

    /**
     * Return map zone by coordinates
     * @param coordinates input coordinates
     * @return zone
     */
    public Zone getZone(Coordinates coordinates){return map[coordinates.getY()][coordinates.getX()];}

    /**
     * Get map zone using integers which define that zone coordinates
     * @param x coordinate x (width)
     * @param y coordinate y (height)
     * @return zone
     */
    public Zone getZone(int x, int y){

        if(x < map[0].length && y < map.length){
            return map[y][x];
        }

        return null;
    }

    /**
     * @return true if zoneCHanging variable is set to true, else it will return false.
     */
    public boolean isZoneChanging() {
        return zoneChanging;
    }

    /**
     * Get map height
     * @return map columns
     */
    public int getMapRows(){
        return map.length;
    }

    /**
     * Get map width
     * @return map rows
     */
    public int getMapColumns(){
        return map[0].length;
    }

    //Set methods
    /**
     * Set zoneChanging variable in to a specific boolean
     * @param zoneChanging boolean
     */
    public void setZoneChanging(boolean zoneChanging) {
        this.zoneChanging = zoneChanging;
    }

    //Abstract methods
    public abstract void setLogicMap();



    /**
     * This function is used to check whether the player is at the border of the zone.
     * @param direction player current direction
     * @return should player change zone? (true, false)
     */
    public boolean shouldPlayerChangeZone(Directions direction, Player player){
        //top border
        return player.getPosition().getY() == 0 && direction == Directions.UP ||
                //left border
                player.getPosition().getX() == 0 && direction == Directions.LEFT ||
                //bottom border
                player.getPosition().getY() == Dimensions.ZONE_ROWS.get()-1 && direction == Directions.DOWN ||
                //right border
                player.getPosition().getX() == Dimensions.ZONE_COLUMNS.get()-1 && direction == Directions.RIGHT;

    }




    public boolean shouldPlayerChangeDungeonZone(Directions direction, Player player){

        //top border
        boolean border =  (player.getPosition().getY() == 1 && direction == Directions.UP ||
                //left border
                player.getPosition().getX() == 1 && direction == Directions.LEFT ||
                //bottom border
                player.getPosition().getY() == Dimensions.ZONE_ROWS.get()-2 && direction == Directions.DOWN ||
                //right border
                player.getPosition().getX() == Dimensions.ZONE_COLUMNS.get()-2 && direction == Directions.RIGHT);

        int nextCurrentMapX = player.getPlayerMapPosition().getX() + direction.getX();
        int nextCurrentMapY = player.getPlayerMapPosition().getY() + direction.getY();

        boolean inBound = nextCurrentMapX >= 0 && nextCurrentMapX < getMap()[0].length && nextCurrentMapY >=0 && nextCurrentMapY < getMap().length;

        if (this instanceof Environment env){
            return inBound && border && env.getEnvironmentType() == EnvironmentType.DUNGEON;
        }

        return false;
    }


    public boolean hasPlayerShouldReturnToOverworld(Player player){

        if(this instanceof Environment env){
            boolean isExit = env.getExitPosition().stream().anyMatch(p-> p.equals(new Position(player.getPlayerMapPosition(), player.getPosition())));
            return isExit && player.getPlayerDirection() == Directions.DOWN;
        }

        return false;
    }


    public Environment getEnvironment(Player player, Directions direction){

        int newX = player.getPosition().getX()+direction.getX();
        int newY = player.getPosition().getY()+direction.getY();

        if (newX >= 0 && newX < Dimensions.ZONE_COLUMNS.get() && newY >= 0 && newY < Dimensions.ZONE_ROWS.get()){
            return this.getZone(player.getPlayerMapPosition()).getTile(new Coordinates(newX, newY)).getEnvironment();

        }

        return null;

    }


    /**
    * This function is used to indicate if player is at the border of the map
    * @param direction current player direction
    * @return true if player is at the border of the map
    */
    public boolean isPlayerAtTheBorderOfTheMap(Directions direction, Player player){

            boolean up    = direction == Directions.UP && player.getPosition().getY() == 0 && player.getPlayerMapPosition().getY()-1 <= 0;
            boolean down  = direction == Directions.DOWN && player.getPosition().getY() == Dimensions.ZONE_ROWS.get()-1 && player.getPlayerMapPosition().getY()+1 >= getMap().length;
            boolean left  = direction == Directions.LEFT && player.getPosition().getX() == 0 &&  player.getPlayerMapPosition().getX()-1 <= 0;
            boolean right = direction == Directions.RIGHT && player.getPosition().getX() == Dimensions.ZONE_COLUMNS.get()-1 && player.getPlayerMapPosition().getX()+1 >= getMap()[0].length;

            return up || down || left || right;

    }


    public void addRupiesInToTheMap(EnemyType enemyType, Position enemyLastPosition){

        int rupies = 0;
        switch (enemyType){
            case OCTOROK -> {rupies = 1;}
            case TEKTITE -> {rupies = 2;}
            case MOLBLIN -> {rupies = 3;}
            default -> {rupies = 0;}
        }
        /*
        getZone(enemyLastPosition.getZoneCoordinates())
                .getTile(enemyLastPosition.getCellCoordinates())
                .setItemType(ItemType.RUPEE);

         */

    }


    public void addRupies(int quantity, Position enemyLastPosition){

        boolean rupieWasAlreadyThere = getZone(enemyLastPosition.getZoneCoordinates())
                .getTile(enemyLastPosition.getCellCoordinates()).getItem() != null;

            //if there is am item in enemyLastPOsition
            if(rupieWasAlreadyThere ) {

                //if item is a Rupie
                if (getZone(enemyLastPosition.getZoneCoordinates()).getTile(enemyLastPosition.getCellCoordinates()).getItem() instanceof Rupie rupie) {
                    rupie.setQuantity(rupie.getQuantity() + quantity);
                }

            }else{
                getZone(enemyLastPosition.getZoneCoordinates())
                        .getTile(enemyLastPosition.getCellCoordinates()).setItem(new Rupie(enemyLastPosition, quantity));
            }




    }





}
