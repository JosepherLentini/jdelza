package com.jdelza.model.characters;

import com.jdelza.model.entities.Entity;
import com.jdelza.model.world.WorldMap;
import com.jdelza.model.world.Zone;
import com.jdelza.utils.enums.DamageType;
import com.jdelza.utils.enums.Dimensions;
import com.jdelza.utils.enums.Directions;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.utils.enums.WeaponType;
import com.jdelza.utils.interfaces.Damageable;
import com.jdelza.utils.interfaces.Movable;
import com.jdelza.utils.interfaces.Usable;
import com.jdelza.model.weapons.Weapon;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;


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
    private boolean isMoving;

    //Health
    private double lifes;                   //Player lifes

    //Items
    private Usable[] inventory;
    private int numberOfRupies;
    private int numberOfBombs;

    //Collision
    private boolean hasCollided;

    //Injure
    private boolean hasPlayerInjuring;

    //weapon & attack
    private Weapon playerWeapon;
    private boolean attacking;
    private boolean throwWeapon;          //Indicates whether or not the player can shoot his sword
    private boolean weaponThrowing;

    /**
     * Contructor
     *
     * @param playerZonePosition player zone position expressed by Coordinates
     */
    private Player(Coordinates playerZonePosition, Coordinates playerMapPosition) {
        super(playerZonePosition);

        //Position
        this.playerMapPosition = playerMapPosition;

        this.lifes = 3.0;
        this.inventory = new Usable[6];

        //Items
        this.numberOfRupies = 0;
        this.numberOfBombs = 0;

        //Direction
        this.playerDirection = Directions.DOWN;
        this.isMoving = false;

        //weapon & attack
        this.throwWeapon = true;
        this.playerWeapon = new Weapon(this.getPosition(), WeaponType.SWORD, getPlayerDirection());
        this.weaponThrowing = false;
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
    public int getNumberOfRupies() {return numberOfRupies;}
    public Coordinates getPlayerMapPosition() {return playerMapPosition;}
    public Directions getPlayerDirection() {return playerDirection;}
    public boolean hasCollided() {return hasCollided;}
    public boolean isHasPlayerInjuring() {return hasPlayerInjuring;}
    public boolean isAttacking() {return attacking;}
    public boolean isThrowWeapon() {return throwWeapon;}
    public Weapon getPlayerWeapon() {return playerWeapon;}
    public boolean isWeaponThrowing() {return weaponThrowing;}

    //Set methods
    public void setLifes(double lifes) {this.lifes = lifes;}
    public void setNumberOfRupies(int numberOfRupies) {this.numberOfRupies = numberOfRupies;}
    public void setPlayerMapPosition(Coordinates playerMapPosition) {this.playerMapPosition = playerMapPosition;}
    public void setPlayerDirection(Directions playerDirection) {this.playerDirection = playerDirection;}
    public void setMoving(boolean moving) { isMoving = moving; }
    public void setHasCollided(boolean collided){this.hasCollided = collided;}
    public void setHasPlayerInjuring(boolean hasPlayerInjuring) {this.hasPlayerInjuring = hasPlayerInjuring;}
    public void setAttacking(boolean attacking) {this.attacking = attacking;}
    public void setPlayerWeapon(Weapon playerWeapon) {this.playerWeapon = playerWeapon;}
    public void setWeaponThrowing(boolean weaponThrowing) {this.weaponThrowing = weaponThrowing;}

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

    public boolean isPlayerAtTheBorderOfTheMap(Directions direction){
        boolean up    = getPosition().getY() == 0 && getPlayerMapPosition().getY() == 0 && direction == Directions.UP;
        boolean down  = getPosition().getY() == Dimensions.ZONE_ROWS.get()-1 && getPlayerMapPosition().getY() == Dimensions.MAP_ROWS.get()-1 && direction == Directions.DOWN;
        boolean left  = getPosition().getX() == 0 && getPlayerMapPosition().getX() == 0 && direction == Directions.LEFT;
        boolean right = getPosition().getX() == Dimensions.ZONE_COLUMNS.get()-1 && getPlayerMapPosition().getX() == Dimensions.MAP_COLUMNS.get()-1 && direction == Directions.RIGHT;

        return up || down || left || right;

    }

    /**
     * This function is used to check whether the player is at the border of the zone.
     * @param direction player current direction
     * @return should player change zone? (true, false)
     */
    public boolean shouldPlayerChangeZone(Directions direction){
                 //top border
        return getPosition().getY() == 0 && direction == Directions.UP ||
                //left border
                getPosition().getX() == 0 && direction == Directions.LEFT ||
                //bottom border
                getPosition().getY() == Dimensions.ZONE_ROWS.get()-1 && direction == Directions.DOWN ||
                //right border
                getPosition().getX() == Dimensions.ZONE_COLUMNS.get()-1 && direction == Directions.RIGHT;

    }

    /**
     * This method return true if enemy will collide with enemy on the next step; else it will return false.
     * @param zone      current player zone
     * @param direction player direction
     * @return boolean
     */
    public boolean hasPlayerCollidedWithEnemy(Zone zone, Directions direction){

        Coordinates nextCoordinates = new Coordinates(
                this.getPosition().getX()+direction.getX(),
                this.getPosition().getY()+direction.getY()
        );

        AtomicBoolean collision = new AtomicBoolean(false);


        zone.getEnemies().stream().map(Entity::getPosition).forEach(
                enemyPosition -> {
                    if (enemyPosition.equals(nextCoordinates) && zone.getTile(enemyPosition).isWalkable()){
                        collision.set(true);
                    }
                }
        );

        return collision.get();

    }

    /**
     * This function returns the enemy that was killed after being hit by the player
     * @param map   current ame map
     * @return      killed enemy
     */
    public Enemy killedEnemy(WorldMap map){

        AtomicReference<Enemy> killedEnemy = new AtomicReference<>();

        map.getZone(getPlayerMapPosition()).getEnemies().forEach(

                (e)-> {
                    if (getPlayerWeapon().getPosition().equals(e.getPosition())) {
                        e.takeDamage(getPlayerWeapon().getAttackPoints());
                        if (e.getLifePoints() <= 0){
                            killedEnemy.set(e);
                        }
                    }

                }

        );


        return killedEnemy.get();

    }

    /**
     * This method allow to end the attack and reset status and weapon infos
     */
    public void playerEndAttack(){
        //set attacking to false
        setAttacking(false);
    }

    /**
     * When the weapon completes its throw,
     * the throw status is set to false and the coordinate is set to the same value as the player’s
     */
    public void endPlayerWeaponThrowing(){
        //set the weapon’s coordinates to be the same as the player’s
        playerWeapon.setPosition(getPosition());

        //Weapon throwing to false
        setWeaponThrowing(false);
    }

    /**
     * It provides the subsequent coordinates for the weapon being thrown.
     * @param newWeaponCoordinates
     */
    public void swordThrowing(Coordinates newWeaponCoordinates){this.playerWeapon.setPosition(newWeaponCoordinates);}

    /**
     * Initialize the weapon’s coordinates and specify whether it will be thrown
     * @param direction                 player direction
     * @param isTheWeaponThrowing       if player has all lives it will be true
     */
    public void initializeAttackWithWeapon(Directions direction, boolean isTheWeaponThrowing){

        //the weapon’s direction is set to match that of the player
        getPlayerWeapon().setWeaponDirection(direction);

        //calculation of the weapon’s position based on the direction provided as input
        Coordinates nextWeaponCoordinates =   new Coordinates(
                this.getPosition().getX()+direction.getX(),
                this.getPosition().getY()+direction.getY()
        );

        //Set the weapon’s ZONE coordinates according to the newly calculated coordinates
        getPlayerWeapon().setPosition(nextWeaponCoordinates);

        //Set the weapon’s coordinates on the MAP
        getPlayerWeapon().setWeaponMapPosition(getPlayerMapPosition());
    }


    @Override
    public void move(Directions direction) {

        //set player direction
        setPlayerDirection(direction);

        //Calculate next player position
        Coordinates newPlayerPosition = new Coordinates(
                this.getPosition().getX() + direction.getX(),
                this.getPosition().getY() + direction.getY()
        );

        //Set next player position
        this.setPosition(newPlayerPosition);

    }

    @Override
    public void move(Zone currentZone) {}


    @Override
    public void takeDamage(DamageType damageType) {

        double newLives = this.getLifes() - damageType.getDamage();
        setHasPlayerInjuring(true);

        if (newLives < 0) {
            this.setLifes(0.0);
        } else {
            this.setLifes(newLives);
        }

    }

    @Override
    public String toString(){
        return "P ";
    }



}
