package com.jdelza.model.characters;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.utils.enums.EnemyType;
import com.jdelza.utils.enums.GameColor;
import com.jdelza.model.weapons.Weapon;


/**
 * This class describe an enemy which attacks the player
 */
public class Enemy extends GameCharacter {

    private int enemyID;

    private double lifePoints;
    private double contactDamage;
    private Weapon weapon;
    private GameColor color;
    private EnemyType enemyType;

    //Enemy zone coordinates
    private Coordinates enemyMapCoordinates; //Defines the coordinates of the enemy

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
    }

    //Get methods
    public double getLifePoints() {return lifePoints;}
    public double getContactDamage() {return contactDamage;}
    public Weapon getWeapon() {return weapon;}
    public GameColor getColor() {return color;}
    public EnemyType getEnemyType() {return enemyType;}
    public Coordinates getEnemyMapCoordinates() {return enemyMapCoordinates;}
    public int getEnemyID() {return enemyID;}

    //Set methods
    public void setLifePoints(double lifePoints) {this.lifePoints = lifePoints;}
    public Enemy setEnemyMapCoordinates(Coordinates enemyMapCoordinates) {this.enemyMapCoordinates = enemyMapCoordinates;return this;}
    public Enemy setEnemyID(int enemyID) {this.enemyID = enemyID;return this;}

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




}
