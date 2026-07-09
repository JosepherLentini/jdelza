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

    static Player playerInstance;
    private double lifes;
    private Weapon slotB;
    private Usable slotA;
    private Usable[] inventory;

    /**
     * Contructor
     * @param position      player position expressed by Coordinates
     */
    private Player(Coordinates position) {
        super(position);
        this.lifes = 3.0;
        this.inventory = new Usable[6];
    }

    /**
     * Singleton constructor
     * @return instance of Player
     */
    public static Player getPlayerInstance(){
        if (playerInstance == null){ playerInstance= new Player(new Coordinates(0,0));}
        return playerInstance;
    }

    //Get methods
    public double getLifes() {return lifes;}
    public Weapon getSlotB() {return slotB;}
    public Usable getSlotA() {return slotA;}
    public Usable[] getInventory() {return inventory;}

    //Set methods
    public void setLifes(double lifes) {this.lifes = lifes;}
    public void setSlotA(Usable slotA) {this.slotA = slotA;}

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
