package com.jdelza.model.characters;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.world.WorldMap;
import com.jdelza.model.world.Zone;
import com.jdelza.utils.enums.*;
import com.jdelza.model.weapons.Weapon;
import com.jdelza.utils.interfaces.Movable;

import java.util.Arrays;
import java.util.Random;

/**
 * This class describe an enemy which attacks the player
 */
public abstract class Enemy extends GameCharacter implements Movable {

    protected String enemyID;

    protected double lifePoints;
    protected double contactDamage;

    //Weapon settings
    protected WeaponType weaponType;         //Indentify the weapon used by enemy
    protected boolean hasWeapon;            //If the enemy has a weapon thi variable is setted to "true"

    protected GameColor color;
    protected EnemyType enemyType;

    //Enemy zone coordinates
    protected Coordinates enemyMapCoordinates; //Defines the coordinates of the enemy

    //Direction
    protected Directions enemyDirection;

    //Steps
    protected int steps;

    //Action settings
    protected Action enemyAction;

    //Collision with player
    protected boolean collidedWithEnemy;


    /**
     * Constructor
     * @param enemyZonePosition     enemy type Coordinates position
     *
     * @param color         color of enemy skin
     * @param enemyType     the typo ef the enemy which contains specifics enemy properties
     */
    public Enemy(Coordinates enemyZonePosition /*Weapon weapon*/, GameColor color, EnemyType enemyType) {
        super(enemyZonePosition);
        this.lifePoints = enemyType.getLifePoints();
        this.contactDamage = enemyType.getContactDamage();
        //this.weapon = weapon;
        this.color = color;
        this.enemyType = enemyType;

        this.hasWeapon = enemyType == EnemyType.TEKTITE;

        //Initialize new random direction
        chooseAndSetDirection();

        //Initialize steps
        chooseandSetNuberOfSteps();

        //Initialize Action
        this.enemyAction = Action.WALK;



    }

    //Get methods
    public double getLifePoints() {return lifePoints;}
    public double getContactDamage() {return contactDamage;}
    public WeaponType getWeaponType() {return weaponType;}
    public GameColor getColor() {return color;}
    public EnemyType getEnemyType() {return enemyType;}
    public Coordinates getEnemyMapCoordinates() {return enemyMapCoordinates;}
    public String getEnemyID() {return enemyID;}
    public Directions getEnemyDirection() {return enemyDirection;}
    public int getSteps() {
        return steps;
    }
    public Action getEnemyAction() {
        return enemyAction;
    }
    public boolean hasWeapon() {return hasWeapon;}
    public boolean isCollidedWithEnemy() {return collidedWithEnemy;}

    //Set methods
    public void setLifePoints(double lifePoints) {this.lifePoints = lifePoints;}
    public Enemy setEnemyMapCoordinates(Coordinates enemyMapCoordinates) {this.enemyMapCoordinates = enemyMapCoordinates;return this;}
    public Enemy setEnemyID(String enemyID) {this.enemyID = enemyID;return this;}
    public void setEnemyDirection(Directions enemyDirection) {this.enemyDirection = enemyDirection;}
    public void setSteps(int steps) {
        this.steps = steps;
    }
    public void setEnemyAction(Action enemyAction) {
        this.enemyAction = enemyAction;
    }
    public void setWeaponType(WeaponType weaponType) {this.weaponType = weaponType; this.hasWeapon = true;}
    public void setCollidedWithEnemy(boolean collidedWithEnemy) {this.collidedWithEnemy = collidedWithEnemy;}

    /*
    @Override
    public void takeDamage(double attackPoints) {
        double damage = this.getLifePoints() - attackPoints;

        if (damage < 0) {
            this.setLifePoints(0.0);
        } else {
            this.setLifePoints(damage);
        }
    }

     */

    public void setColor(GameColor color) {
        this.color = color;
    }

    @Override
    public void move(Directions direction){

        //int newX = this.getPosition().getX() + direction.getX();
        //int newY = this.getPosition().getY() + direction.getY();
        /*
        switch (direction){
            case UP    -> this.setPosition(new Coordinates(this.getPosition().getX(), this.getPosition().getY()-1));
            case DOWN  -> this.setPosition(new Coordinates(this.getPosition().getX(), this.getPosition().getY()+1));
            case LEFT  -> this.setPosition(new Coordinates(this.getPosition().getX()-1, this.getPosition().getY()));
            case RIGHT -> this.setPosition(new Coordinates(this.getPosition().getX()+1, this.getPosition().getY()));
        }

         while (c.getX() < 0 || c.getY() > 16 || !zone.getTile(c).isWalkable()){
            c = randomDirection(e);
        }

         */




    }

    @Override
    public void move(Zone currentZone) {

    }

    public void changePosition(Coordinates newPosition){
        this.setPosition(newPosition);
    }

    protected void chooseandSetNuberOfSteps(){
        Random random = new Random();
        int i = random.nextInt(10)+2;
        this.steps = i;
    }

    public void chooseAndSetAnAction(){
        Random random = new Random();
        int i = random.nextInt(10);
        this.enemyAction = i%2 == 0 ? Action.ATTACK : Action.WALK;

        if (this.getEnemyAction() ==  Action.WALK) {chooseandSetNuberOfSteps(); chooseAndSetDirection();}
    }

    private void chooseAndSetDirection(){
        Random random = new Random();
        int i = random.nextInt(4);
        this.enemyDirection = Directions.values()[i];
    }




    public void resetEnemyAttack(){
        this.setEnemyAction(Action.WALK);
        this.chooseandSetNuberOfSteps();
        this.chooseAndSetDirection();
    }

    //Abstract methods
    public abstract boolean collisionWithPlayer(Player player);


    @Override
    public String toString(){
        return "@" + this.enemyType.getName();
    }






}
