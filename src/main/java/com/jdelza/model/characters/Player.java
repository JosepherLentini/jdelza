package com.jdelza.model.characters;

import com.jdelza.model.world.WorldMap;
import com.jdelza.model.world.Zone;
import com.jdelza.utils.enums.Dimensions;
import com.jdelza.utils.enums.Directions;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.utils.interfaces.Damageable;
import com.jdelza.utils.interfaces.Movable;
import com.jdelza.utils.interfaces.Usable;
import com.jdelza.model.weapons.Weapon;


/**
 * This class define the player of the game
 */
public class Player extends GameCharacter implements Movable, Damageable {


    //Instance
    static Player playerInstance;           //Player instance because class Player is a Singleton

    //Position
    private Coordinates playerMapPosition;  //Player Map coordinates

    //Direction
    private Directions playerDirection;
    private boolean isMoving = false;

    //Health
    private double lifes;                   //Player lifes

    //Slots
    private Weapon slotB;                   //Player item at slot B
    private Usable slotA;                   //Player item at slot A

    //Items
    private Usable[] inventory;
    private int numberOfRupies;
    private int numberOfBombs;

    /**
     * Contructor
     *
     * @param playerZonePosition player zone position expressed by Coordinates
     */
    private Player(Coordinates playerZonePosition, Coordinates playerMapPosition) {
        super(playerZonePosition);
        this.playerMapPosition = playerMapPosition;

        this.lifes = 3.0;
        this.inventory = new Usable[6];
        this.numberOfRupies = 0;
        this.numberOfBombs = 0;

        this.playerDirection = Directions.DOWN;
    }

    /**
     * Singleton constructor
     * @return instance of Player
     */
    public static Player getPlayerInstance(){
        if (playerInstance == null){ playerInstance= new Player(new Coordinates(7,7), new Coordinates(7,7));}
        return playerInstance;
    }

    //Get methods
    public double getLifes() {return lifes;}
    public Weapon getSlotB() {return slotB;}
    public Usable getSlotA() {return slotA;}
    public Usable[] getInventory() {return inventory;}
    public int getNumberOfRupies() {return numberOfRupies;}
    public Coordinates getPlayerMapPosition() {return playerMapPosition;}
    public Directions getPlayerDirection() {return playerDirection;}
    //Set methods
    public void setLifes(double lifes) {this.lifes = lifes;}
    public void setSlotA(Usable slotA) {this.slotA = slotA;}
    public void setSlotB(Weapon slotB) {this.slotB = slotB;}
    public void setNumberOfRupies(int numberOfRupies) {this.numberOfRupies = numberOfRupies;}
    public void setPlayerMapPosition(Coordinates playerMapPosition) {this.playerMapPosition = playerMapPosition;}
    public void setPlayerDirection(Directions playerDirection) {this.playerDirection = playerDirection;}
    public void setMoving(boolean moving) { isMoving = moving; }


    /**
     * Is player moving?
     * @return
     */
    public boolean isMoving() { return isMoving; }

    /**
     * This function is used to insert a Usable item into the inventory
     * @param item  Usable item to add o the inventory
     */
    public void addItemToInventory(Usable item){
        int index = 0;
        while (index<inventory.length && inventory[index] != null){
            index += 1;
        }
        if (index<inventory.length){inventory[index] = item;}
    }

    public void walk(WorldMap map, Directions direction){
        Zone currentZone = map.getZone(this.getPlayerMapPosition());

        Coordinates nextCoordinates  =new Coordinates(
                this.getPosition().getX() + direction.getX(),
                this.getPosition().getY() + direction.getY()
        );
        //move(direction);
        System.out.println(currentZone.getZone()[nextCoordinates.getY()][nextCoordinates.getX()].isWalkable());

        if (currentZone.getZone()[nextCoordinates.getY()][nextCoordinates.getX()].isWalkable()){
            move(direction);
        }



    }

    @Override
    public void move(Directions direction) {


        this.setPosition(new Coordinates(
                this.getPosition().getX() + direction.getX(),
                this.getPosition().getY() + direction.getY()
        ));
        //System.out.println("Zone: "+ this.getPosition());
        //System.out.println("Map: " + this.getPlayerMapPosition());
        //System.out.println("Model: player");
    }

    public void changeZone(Directions direction){
        switch (direction){
            case UP: {
                super.setPosition(new Coordinates(super.getPosition().getX(), Dimensions.ZONE_ROWS.get()-1));
                playerMapPosition.setY(playerMapPosition.getY()-1);

            }; break;
            case DOWN:{
                super.setPosition(new Coordinates(super.getPosition().getX(), 0));
                playerMapPosition.setY(playerMapPosition.getY()+1);

            }; break;
            case LEFT:{
                super.setPosition(new Coordinates(Dimensions.ZONE_COLUMNS.get()-1, super.getPosition().getY()));
                playerMapPosition.setX(playerMapPosition.getX()-1);

            }; break;
            case RIGHT:{
                super.setPosition(new Coordinates(0, super.getPosition().getY()));
                playerMapPosition.setX(playerMapPosition.getX()+1);

            }; break;
        }
    }


    @Override
    public String toString(){
        return "P ";
    }

    @Override
    public void takeDamage(double attackPoints) {
        double damage = this.getLifes() - attackPoints;

        if (damage < 0) {
            this.setLifes(0.0);
        } else {
            this.setLifes(damage);
        }
    }





}
