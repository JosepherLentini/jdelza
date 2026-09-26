package com.jdelza.model.weapons;

import com.jdelza.model.characters.Player;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.entities.Entity;
import com.jdelza.model.world.Zone;
import com.jdelza.utils.enums.DamageType;
import com.jdelza.utils.enums.Directions;
import com.jdelza.utils.enums.WeaponType;
import com.jdelza.utils.interfaces.Damageable;
import com.jdelza.utils.interfaces.Damager;

/**
 * Weapon represent the type of weapon used by enemies or player
 */
public class Weapon extends Entity implements Damager {

    private DamageType attackPoints;
    private WeaponType weapon;

    //Map position
    private Coordinates weaponMapPosition;

    //Type
    private WeaponType weaponType;

    //Weapon direction
    private Directions weaponDirection;

    //ID
    private String weaponID;

    //Range
    private int weaponRange;     //This is the maximum number of cells within which it operates in the area



    /**
     * Constructor
     * @param position      coordinates of weapon
     */
    public Weapon(Coordinates position, WeaponType weaponType, Directions weaponDirection) {
        super(position);
        this.weapon = weapon;
        this.weaponDirection =  weaponDirection;
        this.weaponType = weaponType;

        this.attackPoints = weapon == WeaponType.SWORD ? DamageType.NORMAL : DamageType.LIGHT;
        this.weaponRange = 10;
    }

    //Get methods
    public DamageType getAttackPoints() {
        return attackPoints;
    }
    public Coordinates getWeaponMapPosition() {return weaponMapPosition;}
    public Directions getWeaponDirection() {return weaponDirection;}
    public int getWeaponRange() {
        return this.weaponRange;
    }
    public String getWeaponID() {
        return weaponID;
    }
    public WeaponType getWeaponType() {return weaponType;}

    //Set methods
    public void setWeaponMapPosition(Coordinates weaponMapPosition) {this.weaponMapPosition = weaponMapPosition;}
    public void setWeaponDirection(Directions weaponDirection) {this.weaponDirection = weaponDirection;}

    public Weapon setWeaponID() {
        if (weaponID == null){
            this.weaponID = this.weaponMapPosition.getX()+""+this.weaponMapPosition.getY()+""+this.getPosition().getX()+""+this.getPosition().getY();
        }


        return this;
    }

    /**
     * Thies method allow to set or modify the weaponRange
     * @param weaponRange
     */
    public void setWeaponRange(int weaponRange) {
        this.weaponRange = weaponRange;
    }


    public void moveWeapon(Zone zone){
        Coordinates newPosition = new Coordinates(0,0);

        switch (weaponDirection){
            case UP    -> newPosition = new Coordinates(this.getPosition().getX(), this.getPosition().getY()-1);
            case DOWN  -> newPosition =  new Coordinates(this.getPosition().getX(), this.getPosition().getY()+1);
            case LEFT  -> newPosition =  new Coordinates(this.getPosition().getX()-1, this.getPosition().getY());
            case RIGHT -> newPosition =  new Coordinates(this.getPosition().getX()+1, this.getPosition().getY());
        }

        boolean isWalkable = false;

        try {
            isWalkable = zone.getTile(newPosition).isWalkable();
        } catch (IndexOutOfBoundsException e) {

            isWalkable = false;
        }


        if (isWalkable) {
            this.setPosition(newPosition);
            this.setWeaponRange(this.getWeaponRange() - 1);
        } else {
            this.setWeaponRange(0);
        }
    }

    @Override
    public void toDamage(Damageable damageable) {
        //When a collision occurs between sword and game characters, points are deducted
        damageable.takeDamage(this.attackPoints.getDamage());
    }

    public boolean hasCollidedWithPlayer(Player player){
        return player.getPosition().equals(getPosition());
    }

}
