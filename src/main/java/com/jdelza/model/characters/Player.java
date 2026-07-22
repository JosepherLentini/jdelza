package com.jdelza.model.characters;

import com.jdelza.model.enums.Directions;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.interfaces.Damageable;
import com.jdelza.model.interfaces.Movable;
import com.jdelza.model.interfaces.Usable;
import com.jdelza.model.weapons.Weapon;


/**
 * This class define the player of the game
 */
public class Player extends GameCharacter implements Movable, Damageable {


    //Instance
    static Player playerInstance;           //Player instance because class Player is a Singleton

    //Position
    private Coordinates playerMapPosition;  //Player Map coordinates

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
    }

    /**
     * Singleton constructor
     * @return instance of Player
     */
    public static Player getPlayerInstance(){
        if (playerInstance == null){ playerInstance= new Player(new Coordinates(0,0), new Coordinates(0,0));}
        return playerInstance;
    }

    //Get methods
    public double getLifes() {return lifes;}
    public Weapon getSlotB() {return slotB;}
    public Usable getSlotA() {return slotA;}
    public Usable[] getInventory() {return inventory;}
    public int getNumberOfRupies() {return numberOfRupies;}
    public Coordinates getPlayerMapPosition() {return playerMapPosition;}

    //Set methods
    public void setLifes(double lifes) {this.lifes = lifes;}
    public void setSlotA(Usable slotA) {this.slotA = slotA;}
    public void setSlotB(Weapon slotB) {this.slotB = slotB;}
    public void setNumberOfRupies(int numberOfRupies) {this.numberOfRupies = numberOfRupies;}
    public void setPlayerMapPosition(Coordinates playerMapPosition) {this.playerMapPosition = playerMapPosition;}

    /**
     * This function is used to insert an Usable item into the inventory
     * @param item  Usable item to add o the inventory
     */
    public void addItemToInventory(Usable item){
        int index = 0;
        while (index<inventory.length && inventory[index] != null){
            index += 1;
        }
        if (index<inventory.length){inventory[index] = item;}
    }

    @Override
    public void move(Directions direction) {
        this.setPosition(new Coordinates(
                this.getPosition().getX() + direction.getX(),
                this.getPosition().getY() + direction.getY()
        ));
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
