package com.jdelza.model.characters;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.enums.EnemyType;
import com.jdelza.model.enums.SkinColor;
import com.jdelza.model.interfaces.Damageable;
import com.jdelza.model.interfaces.Movable;
import com.jdelza.model.weapons.Weapon;


/**
 * This class describe an enemy which attacks the player
 */
public abstract class Enemy extends GameCharacter implements Movable, Damageable {

    private double lifePoints;
    private double contactDamage;
    private Weapon weapon;
    private SkinColor color;
    private EnemyType enemyType;

    /**
     * Constructor
     * @param position      enemy type Coordinates position
     * @param weapon        weapon which enemy uses
     * @param color         color of enemy skin
     * @param enemyType     the typo ef the enemy which contains specifics enemy properties
     */
    public Enemy(Coordinates position, Weapon weapon, SkinColor color, EnemyType enemyType) {
        super(position);
        this.lifePoints = enemyType.getLifePoints();
        this.contactDamage = enemyType.getContactDamage();
        this.weapon = weapon;
        this.color = color;
        this.enemyType = enemyType;
    }

    //Get methods
    public double getLifePoints() {return lifePoints;}
    public double getContactDamage() {return contactDamage;}
    public Weapon getWeapon() {return weapon;}
    public SkinColor getColor() {return color;}
    public EnemyType getEnemyType() {return enemyType;}


    //Set methods
    public void setLifePoints(double lifePoints) {this.lifePoints = lifePoints;}


    @Override
    public void takeDamage(double attackPoints) {
        double damage = this.getLifePoints() - attackPoints;

        if (damage < 0) {
            this.setLifePoints(0.0);
        } else {
            this.setLifePoints(damage);
        }
    }




}
