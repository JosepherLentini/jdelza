package com.jdelza.model.characters;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.utils.enums.*;
import com.jdelza.utils.interfaces.Damageable;
import com.jdelza.utils.interfaces.Movable;

import java.util.Random;

/**
 * This class describe an enemy which attacks the player
 */
public abstract class Enemy extends GameCharacter implements Movable, Damageable {

    protected String enemyID;

    protected double lifePoints;
    protected DamageType damageType;

    //Weapon settings
    protected WeaponType weaponType;         //Identify the weapon used by enemy
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


    /**
     * Constructor
     * @param enemyZonePosition     enemy type Coordinates position
     *
     * @param color         color of enemy skin
     * @param enemyType     the typo ef the enemy which contains specifics enemy properties
     */
    public Enemy(Coordinates enemyZonePosition, GameColor color, EnemyType enemyType) {
        super(enemyZonePosition);
        this.lifePoints = enemyType.getLifePoints();
        this.damageType = enemyType.getCollisionDamage();
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
    public DamageType getDamageType() {return damageType;}
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
    public void setColor(GameColor color) {
        this.color = color;
    }

    //Abstract methods
    public abstract boolean hasEnemyCollidedWithEnemy(Player player);

    /**
     * Choose random number of steps
     */
    protected void chooseandSetNuberOfSteps(){
        Random random = new Random();
        //Random steps
        int i = random.nextInt(10)+2;

        //Set steps
        this.steps = i;
    }

    /**
     * Choose random action
     */
    public void chooseAndSetAnAction(){
        Random random = new Random();
        //Random int 0-9
        int i = random.nextInt(10);

        //if random int is odd or even
        this.enemyAction = i%2 == 0 ? Action.ATTACK : Action.WALK;

        //Set the enemy action
        if (this.getEnemyAction() ==  Action.WALK) {chooseandSetNuberOfSteps(); chooseAndSetDirection();}
    }

    /**
     * Choose random direction
     */
    private void chooseAndSetDirection(){
        Random random = new Random();
        //a random number within a range from 0 up to the number of elements in the direction enumeration
        int i = random.nextInt(Directions.values().length);
        //set enemy direction
        this.enemyDirection = Directions.values()[i];
    }

    /**
     * At the end of an attack, set the state to WALK
     */
    public void resetEnemyAttack(){
        //Set status to walk
        this.setEnemyAction(Action.WALK);
        //Choose number of steps
        this.chooseandSetNuberOfSteps();
        //Choose direction
        this.chooseAndSetDirection();
    }

    /**
     * This function returns true if the enemy and the player are in the same position
     * @param player player
     * @return has enemy collided?
     */
    public boolean collisionWithPlayerWeapon(Player player){
        //is enemy position equals to player position?
        return getPosition().equals(player.getPlayerWeapon().getPosition());
    }

    @Override
    public void takeDamage(DamageType damage){
        setLifePoints(getLifePoints() - damage.getDamage());

    }

    @Override
    public String toString(){
        return "@" + this.enemyType.getName();
    }






}
