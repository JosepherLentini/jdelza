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

    public double lifePoints;
    public double collisionPoints;
    private Weapon weapon;
    private SkinColor color;
    private EnemyType enemyType;

    public Enemy(Coordinates position, Weapon weapon, SkinColor color, EnemyType enemyType) {
        super(position);
        this.lifePoints = enemyType.getLifePoints();
        this.collisionPoints = enemyType.getCollisionPoints();
        this.weapon = weapon;
        this.color = color;
        this.enemyType = enemyType;
    }
    //Get methods
    public double getLifePoints() {return lifePoints;}
    public double getAttackPoints() {return collisionPoints;}
    public Weapon getWeapon() {return weapon;}
    public SkinColor getColor() {return color;}
    public EnemyType getEnemyType() {return enemyType;}


    //Set methods


    public void setLifePoints(double lifePoints) {
        this.lifePoints = lifePoints;
    }


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
